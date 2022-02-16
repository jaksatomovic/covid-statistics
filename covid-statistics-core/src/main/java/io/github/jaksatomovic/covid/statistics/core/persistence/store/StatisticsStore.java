package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.StatisticsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class StatisticsStore
    extends Store<Long, DbStatistics, StatisticsRepository>
{
    protected StatisticsStore(final StatisticsRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
    }

    @Override
    protected Class<DbStatistics> getEntityClass()
    {
        return DbStatistics.class;
    }
}
