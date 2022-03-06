package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.commons.utility.CovidStatisticsServiceException;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbEntity;
import io.github.jaksatomovic.covid.statistics.core.utility.PersistenceClosure;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Represents an abstract implementation of data access layer that uses [{@link CrudRepository}] for accessing
 * persistence store.<br>
 * It provides basic methods that enable querying, inserting, updating and deleting entities.
 *
 * @param <K> the type parameter
 * @param <E> the type parameter
 * @param <R> the type parameter
 * @author Jaksa Tomovic
 * @since 1.0
 */
public abstract class Store<K extends Serializable & Comparable<K>, E extends DbEntity<K, ? super E>, R extends CrudRepository<E, K> & JpaSpecificationExecutor<E>>
{
    private static final Logger logger = LoggerFactory.getLogger(Store.class);

    private final R repository;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Instantiates a new Store.
     *
     * @param repository    the repository
     * @param entityManager the entity manager
     */
    protected Store(R repository, EntityManager entityManager)
    {
        this.repository = Defense.notNull(repository, "repository");
        this.entityManager = Defense.notNull(entityManager, "entity manager");
    }

    /**
     * Gets entity class.
     *
     * @return the entity class
     */
    protected abstract Class<E> getEntityClass();

    /**
     * Gets current session.
     *
     * @return the current session
     */
    public Session getCurrentSession()
    {
        return entityManager.unwrap(Session.class);
    }

    /**
     * Gets entity manager.
     *
     * @return the entity manager
     */
    protected EntityManager getEntityManager()
    {
        return entityManager;
    }

    /**
     * Saves an entity to persistence store
     *
     * @param entity the entity
     */
    public E saveEntity(E entity)
    {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * Save all entities.
     *
     * @param entities the entities
     */
    public void saveAllEntities(List<E> entities)
    {
        for (E entity : entities)
        {
            entityManager.persist(entity);
        }
    }

    /**
     * Saves an entity to persistence store
     *
     * @param entity the entity
     * @param flush  the flush
     */
    public void saveEntity(E entity, boolean flush)
    {
        saveEntity(entity);

        if (flush)
        {
            entityManager.flush();
        }
    }

    /**
     * Deletes an entity from persistence store
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteEntity(long id)
    {
        Object obj = getEntity(id);
        entityManager.remove(obj);
        return true;
    }

    /**
     * Deletes an entity from persistence store
     *
     * @param entity the entity
     * @return the boolean
     */
    public boolean deleteEntity(E entity)
    {
        entityManager.remove(entity);
        return true;
    }

    /**
     * Delete entities boolean.
     *
     * @param entities the entities
     * @return the boolean
     */
    public boolean deleteEntities(List<E> entities)
    {
        for (E entity : entities)
        {
            entityManager.remove(entity);
        }
        return true;
    }

    /**
     * Delete all entities boolean.
     *
     * @return the boolean
     */
    public boolean deleteAllEntities()
    {
        List<E> entities = this.loadTable();

        for (E entity : entities)
        {
            entityManager.remove(entity);
        }
        return true;
    }

    /**
     * Loads an entity from persistence store
     *
     * @param id the id
     * @return the entity
     */
    public E getEntity(long id)
    {
        E entity = entityManager.find(getEntityClass(), id);

        return entity;
    }

    /**
     * Loads all entities from persistence store
     *
     * @return the list
     */
    public List<E> loadTable()
    {
        CriteriaBuilder  cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<E> q  = cb.createQuery(getEntityClass());

        Root<E> s = q.from(getEntityClass());
        q.select(s);

        List<E> result = getEntityManager().createQuery(q).getResultList();

        return result;
    }

    /**
     * Converts the given predicate collection into its corresponding predicate array.<br>
     *
     * @param predicates [{@link Collection}&lt;{@link Predicate}&gt;] :: the (mandatory) predicates
     * @return predicates [{@link Predicate}[]] :: the predicates array
     */
    protected final Predicate[] clause(Collection<Predicate> predicates)
    {
        return Defense.notNull(predicates, "criteria query predicates").toArray(new Predicate[0]);
    }

    /**
     * Executes the given closure <b>without</b> a database transaction.<br>
     *
     * @param <R>     the type parameter
     * @param closure [{@link PersistenceClosure}&lt;R&gt;] :: the closure
     * @return result [&lt;R&gt;] :: the closure result
     */
    protected <R> R execute(PersistenceClosure<R> closure)
    {
        Defense.notNull(closure, "closure");

        try
        {
            return closure.execute(entityManager);
        }
        catch (NoResultException exception)
        {
            return null;
        }
        catch (Exception exception)
        {
            throw CovidStatisticsServiceException.internalError("Unable to execute persistence closure");
        }
    }
}