package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.core.features.cleanup.shared.CleanupBaseContextCreator;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class CleanupContextCreator
    extends CleanupBaseContextCreator<CleanupContext, CleanupRequest>
{

    /**
     * Instantiates a new Cleanup base context creator.
     *
     * @param searchStore the search store
     */
    protected CleanupContextCreator(final SearchStore searchStore)
    {
        super(searchStore);
    }

    @Override
    public CleanupContext create(final CleanupRequest request)
    {
        Optional<List<DbSearch>> matchingSearches = Optional.ofNullable(searchStore.loadTable());

        return new CleanupContext()
        {
            @Override
            public Optional<List<DbSearch>> getAllSearches()
            {
                return matchingSearches;
            }

            @Override
            public CleanupRequest getOriginalRequest()
            {
                return request;
            }
        };
    }
}
