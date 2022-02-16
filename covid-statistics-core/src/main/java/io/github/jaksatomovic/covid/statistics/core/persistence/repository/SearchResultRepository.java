package io.github.jaksatomovic.covid.statistics.core.persistence.repository;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearchResult;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Repository
public interface SearchResultRepository
    extends CrudRepository<DbSearchResult, Long>, JpaSpecificationExecutor<DbSearchResult>
{
}
