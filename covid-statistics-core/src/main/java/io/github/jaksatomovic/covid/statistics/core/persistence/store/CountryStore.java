package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Check;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.CountryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Country store.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class CountryStore
    extends Store<Long, DbCountry, CountryRepository>
{
    private final CountryRepository repository;

    /**
     * Instantiates a new Country store.
     *
     * @param repository    the repository
     * @param entityManager the entity manager
     */
    protected CountryStore(final CountryRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
        this.repository = Defense.notNull(repository, CountryRepository.class.getSimpleName());
    }

    public CountryRepository getRepository()
    {
        return repository;
    }

    @Override
    protected Class<DbCountry> getEntityClass()
    {
        return DbCountry.class;
    }

    /**
     * Fetch all countries optional.
     *
     * @return the optional
     */
    public Optional<Map<String, Long>> fetchAllCountries()
    {
        List<DbCountry> dbCountries = loadTable();

        if (Check.notEmpty(dbCountries))
        {
            return Optional.of(dbCountries.stream()
                                          .collect(Collectors.toMap(DbCountry::getName, DbCountry::getId)));
        }

        return Optional.empty();
    }
}
