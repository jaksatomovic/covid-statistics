package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContextCreator;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class DeleteStatisticsContextCreator
    extends StatisticsContextCreator<DeleteStatisticsContext, DeleteStatisticsRequest>
{

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     */
    protected DeleteStatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore)
    {
        super(statisticsStore, searchStore, searchResultStore);
    }

    @Override
    public DeleteStatisticsContext create(final DeleteStatisticsRequest request)
    {

        Optional<DbSearch> getExistingOverlappingSearch = searchStore.getIfOverlapsBy(request.getCountry(), request.getDateFrom(), request.getDateTo());

        return new DeleteStatisticsContext()
        {
            @Override
            public Optional<DbSearch> getExistingSearch()
            {
                return getExistingOverlappingSearch;
            }

            @Override
            public DeleteStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }
}
