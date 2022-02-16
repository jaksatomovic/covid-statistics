package io.github.jaksatomovic.covid.statistics.core.persistence.repository;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Repository
public interface CountryRepository
    extends CrudRepository<DbCountry, Long>, JpaSpecificationExecutor<DbCountry>
{
    List<DbCountry> findAll();
}
