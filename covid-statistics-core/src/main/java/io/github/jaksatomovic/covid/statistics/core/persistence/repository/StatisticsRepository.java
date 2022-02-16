package io.github.jaksatomovic.covid.statistics.core.persistence.repository;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Repository
public interface StatisticsRepository
    extends CrudRepository<DbStatistics, Long>, JpaSpecificationExecutor<DbStatistics>
{
}
