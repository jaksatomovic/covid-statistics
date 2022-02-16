package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.SearchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class SearchStore
    extends Store<Long, DbSearch, SearchRepository>
{
    private final SearchRepository repository;

    protected SearchStore(final SearchRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
        this.repository = Defense.notNull(repository, SearchRepository.class.getSimpleName());
    }

    @Override
    protected Class<DbSearch> getEntityClass()
    {
        return DbSearch.class;
    }

    public Optional<DbSearch> getIfOverlapsBy(final String country, final LocalDate dateFrom, final LocalDate dateTo)
    {
        // TODO
        return Optional.empty();
    }
}
