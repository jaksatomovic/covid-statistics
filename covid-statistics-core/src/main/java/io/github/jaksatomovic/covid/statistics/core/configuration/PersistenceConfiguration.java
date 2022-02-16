package io.github.jaksatomovic.covid.statistics.core.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.commons.utility.CovidStatisticsServiceException;
import io.github.jaksatomovic.covid.statistics.core.configuration.properties.DatabaseProperties;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbEntity;
import io.github.jaksatomovic.covid.statistics.core.utility.Closure;
import io.github.jaksatomovic.covid.statistics.core.utility.PersistenceClosure;
import io.github.jaksatomovic.covid.statistics.core.utility.TransactionalExecutor;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.hibernate.tool.schema.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Persistence Configuration.<br>
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfiguration
{
    private static final Logger logger = LoggerFactory.getLogger(PersistenceConfiguration.class);

    private static final String APPLICATION_NAME     = "Covid Statistics";
    private static final String TIMEZONE_UTC         = "UTC";
    private static final String SESSION_TIME_ZONE    = "sessionTimeZone";
    private static final String CONNECTION_POOL      = "covid-statistics-database-connection-pool";
    private static final String CHANGELOG_MASTER_XML = "classpath:db.changelog-master.xml";

    private final DatabaseProperties databaseProperties;

    public PersistenceConfiguration(final DatabaseProperties databaseProperties)
    {
        this.databaseProperties = Defense.notNull(databaseProperties, DatabaseProperties.class.getSimpleName());
    }

    /**
     * Gets data source.
     *
     * @return dataSource [{@link DataSource}] :: the data source
     */
    @Bean (name = "dataSource")
    public DataSource getDataSource()
    {
        final HikariConfig config = new HikariConfig();

        config.setJdbcUrl(databaseProperties.getDatabaseJdbcUrl());
        config.setUsername(databaseProperties.getUsername());
        config.setPassword(databaseProperties.getPassword());
        config.setDriverClassName(databaseProperties.getDriverClassName().getCanonicalName());
        config.setMinimumIdle(databaseProperties.getMinimumIdle());
        config.setMaximumPoolSize(databaseProperties.getMaximumPoolSize());
        config.setAutoCommit(false);
        config.addDataSourceProperty(SESSION_TIME_ZONE, TIMEZONE_UTC);
        config.setPoolName(CONNECTION_POOL);
        config.setRegisterMbeans(false);

        final HikariDataSource dataSource = new HikariDataSource(config);

        logger.debug("Created data source");

        return dataSource;
    }

    /**
     * Gets jpa configuration.
     *
     * @return jpaProperties [{@link Map}&lt;{@link String}, {@link Object}&gt;] :: the jpa configuration
     */
    @Bean (name = "jpaProperties")
    public Map<String, Object> getJpaProperties()
    {
        final Map<String, Object> jpaProperties = new HashMap<>();

        jpaProperties.put("hibernate.dialect", PostgreSQL9Dialect.class.getCanonicalName());
        jpaProperties.put("hibernate.id.new_generator_mappings", true);
        jpaProperties.put("hibernate.show_sql", false);
        jpaProperties.put("hibernate.hbm2ddl.auto", Action.VALIDATE); // never use update here, let the database manager take care for it
        jpaProperties.put("hibernate.connection.autocommit", false);
        jpaProperties.put("hibernate.cache.provider_class", "org.hibernate.cache.internal.NoCacheProvider");
        jpaProperties.put("hibernate.cache.use_query_cache", false);
        jpaProperties.put("hibernate.cache.use_second_level_cache", false);
        jpaProperties.put("hibernate.temp.use_jdbc_metadata_defaults", false);

        return jpaProperties;
    }

    /**
     * Gets entity manager factory.
     *
     * @return entityManagerFactory [{@link EntityManagerFactory}] :: the entity manager factory
     */
    @Bean (name = "entityManagerFactory",
           destroyMethod = "close")
    @DependsOn ("liquibase")
    public EntityManagerFactory getEntityManagerFactory()
    {
        final LocalContainerEntityManagerFactoryBean entityManagerFactory;
        entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setDataSource(getDataSource());
        entityManagerFactory.setJpaPropertyMap(getJpaProperties());
        entityManagerFactory.setPackagesToScan(DbEntity.class.getPackage().getName());
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPersistenceUnitName(APPLICATION_NAME);
        entityManagerFactory.afterPropertiesSet();

        return entityManagerFactory.getObject();
    }

    /**
     * Gets entity manager.
     *
     * @return entityManager [{@link EntityManager}] :: the entity manager
     */
    @Primary
    @Bean (name = "entityManager")
    public EntityManager getEntityManager()
    {
        final SharedEntityManagerBean manager = new SharedEntityManagerBean();

        manager.setEntityManagerFactory(getEntityManagerFactory());
        manager.afterPropertiesSet();

        return manager.getObject();
    }

    /**
     * Gets transaction manager.
     *
     * @return transactionManager [{@link PlatformTransactionManager}] :: the transaction manager
     */
    @Bean (name = "transactionManager")
    @DependsOn ("liquibase")
    public PlatformTransactionManager getTransactionManager()
    {
        final JpaTransactionManager transactionManager = new JpaTransactionManager(getEntityManagerFactory());

        transactionManager.afterPropertiesSet();

        return transactionManager;
    }

    /**
     * Gets transactional executor.
     *
     * @return transactionalExecutor [{@link TransactionalExecutor}] :: the transactional executor
     */
    @Bean (name = "transactionExecutor")
    public TransactionalExecutor getTransactionalExecutor()
    {
        final DefaultTransactionAttribute definition = new DefaultTransactionAttribute();

        definition.setName(APPLICATION_NAME);
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        return new TransactionalExecutor()
        {
            private final EntityManager manager = getEntityManager();
            private final TransactionTemplate template = new TransactionTemplate(getTransactionManager(), definition);

            @Override
            public <R> R transactional(PersistenceClosure<R> closure)
            {
                return template.execute(status -> closure.execute(manager));
            }

            @Override
            public <R> R transactional(Closure<R> closure)
            {
                return template.execute(status -> disarm(closure));
            }

            /**
             * Disarms the given closure.<br>
             * That is, either re-throwing a billing service exception or wrap one aroun the root cause.<br>
             *
             * @param closure [{@link Closure}] :: the (mandatory) closure to execute
             *
             * @return result [&lt;R&gt;] :: the result of the closure
             */
            private <R> R disarm(Closure<R> closure)
            {
                Defense.notNull(closure, "closure");

                try
                {
                    return closure.execute();
                }
                catch (CovidStatisticsServiceException exception)
                {
                    throw exception;
                }
                catch (Exception exception)
                {
                    throw CovidStatisticsServiceException.internalError(String.format("Unable to execute transactional run closure -> %s", exception.getMessage()));
                }
            }
        };
    }

    /**
     * Gets transaction interceptor.
     *
     * @return pmTransactionInterceptor [{@link TransactionInterceptor}] :: the transaction interceptor
     */
    @Bean (name = "applicationTransactionInterceptor")
    public TransactionInterceptor getTransactionInterceptor()
    {
        return buildTransactionInterceptor();
    }

    /**
     * Transactional p.
     *
     * @param <P>         [&lt;P&gt;] :: the type parameter
     * @param peerClass   [{@link Class}&lt;P&gt;] :: the peer class
     * @param peer        [&lt;P&gt;] :: the peer
     * @param interceptor [{@link TransactionInterceptor}] :: the interceptor
     * @return the p
     */
    protected final <P> P transactional(Class<P> peerClass, P peer, TransactionInterceptor interceptor)
    {
        Defense.notNull(peerClass, "peer class");
        Defense.notNull(peer, "peer instance");
        Defense.notNull(interceptor, "transaction interceptor");

        final AspectJProxyFactory factory = new AspectJProxyFactory();

        factory.setTarget(peer);
        factory.addAdvice(interceptor);
        factory.setProxyTargetClass(true); // use CGLIB

        return peerClass.cast(factory.getProxy());
    }

    /**
     * Builds transaction interceptor.
     *
     * @return transactionInterceptor [{@link TransactionInterceptor}] :: the transaction interceptor
     */
    private TransactionInterceptor buildTransactionInterceptor()
    {
        final DefaultTransactionAttribute           definition  = new DefaultTransactionAttribute();
        final MatchAlwaysTransactionAttributeSource source      = new MatchAlwaysTransactionAttributeSource();
        final TransactionInterceptor                interceptor = new TransactionInterceptor();

        definition.setName(APPLICATION_NAME);
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        source.setTransactionAttribute(definition);

        interceptor.setTransactionManager(getTransactionManager());
        interceptor.setTransactionAttributeSource(source);
        interceptor.afterPropertiesSet();

        logger.debug("Created transaction interceptor");

        return interceptor;
    }

    @Bean (name = "liquibase")
    @DependsOn ("dataSource")
    public SpringLiquibase liquibase()
    {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog(CHANGELOG_MASTER_XML);
        liquibase.setDataSource(getDataSource());
        return liquibase;
    }
}
