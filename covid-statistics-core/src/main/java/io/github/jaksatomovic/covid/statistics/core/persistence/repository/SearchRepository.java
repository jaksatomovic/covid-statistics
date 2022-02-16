package io.github.jaksatomovic.covid.statistics.core.persistence.repository;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Search repository.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Repository
public interface SearchRepository
    extends CrudRepository<DbSearch, Long>, JpaSpecificationExecutor<DbSearch>
{
}
