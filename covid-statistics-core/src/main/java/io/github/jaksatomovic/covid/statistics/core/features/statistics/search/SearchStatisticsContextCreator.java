package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiClient;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContextCreator;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class SearchStatisticsContextCreator
    extends StatisticsContextCreator<SearchStatisticsContext, SearchStatisticsRequest>
{
    private final RapidApiClient rapidApiClient;

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     * @param rapidApiClient
     */
    protected SearchStatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore, final RapidApiClient rapidApiClient)
    {
        super(statisticsStore, searchStore, searchResultStore);
        this.rapidApiClient = Defense.notNull(rapidApiClient, RapidApiClient.class.getSimpleName());
    }

    @Override
    public SearchStatisticsContext create(final SearchStatisticsRequest request)
    {
        GetHistoryResponse casesFrom = fetchHistoryForDate(resolveGetHistoryRequest(request.getCountry(), request.getDateFrom()));
        GetHistoryResponse casesTo   = fetchHistoryForDate(resolveGetHistoryRequest(request.getCountry(), request.getDateTo()));

        Optional<DbSearch> getExistingOverlappingSearch = searchStore.getIfOverlapsBy(request.getCountry(), request.getDateFrom(), request.getDateTo());

        return new SearchStatisticsContext()
        {
            @Override
            public Optional<GetHistoryResponse> getCasesFrom()
            {
                return Optional.ofNullable(casesFrom);
            }

            @Override
            public Optional<GetHistoryResponse> getCasesTo()
            {
                return Optional.ofNullable(casesTo);
            }

            @Override
            public Optional<DbSearch> getExistingSearch()
            {
                return getExistingOverlappingSearch;
            }

            @Override
            public SearchStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    private GetHistoryRequest resolveGetHistoryRequest(final String country, final LocalDate date)
    {
        GetHistoryRequest getHistoryRequest = new GetHistoryRequest();
        getHistoryRequest.setCountry(country);
        getHistoryRequest.setDate(date);

        return getHistoryRequest;
    }

    private GetHistoryResponse fetchHistoryForDate(final GetHistoryRequest request)
    {
        return rapidApiClient.fetchHistory(request);
    }
}
