package io.github.jaksatomovic.covid.statistics.core.persistence.store;

import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearchResult;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.SearchResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
@Transactional
public class SearchResultStore
    extends Store<Long, DbSearchResult, SearchResultRepository>
{
    protected SearchResultStore(final SearchResultRepository repository, final EntityManager entityManager)
    {
        super(repository, entityManager);
    }

    @Override
    protected Class<DbSearchResult> getEntityClass()
    {
        return DbSearchResult.class;
    }
}
