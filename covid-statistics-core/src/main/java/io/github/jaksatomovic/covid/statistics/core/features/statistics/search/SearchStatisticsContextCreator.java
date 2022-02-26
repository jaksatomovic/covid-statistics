package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.commons.utility.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.exception.AppException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiClient;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.Mutable;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContextCreator;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.CountryStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class SearchStatisticsContextCreator
    extends StatisticsContextCreator<SearchStatisticsContext, SearchStatisticsRequest>
{
    private static final String DB_STATISTICS = "dbStatistics";

    private final RapidApiClient rapidApiClient;
    private final CountryStore   countryStore;

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     * @param rapidApiClient    the rapid api client
     * @param countryStore
     */
    protected SearchStatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore, final RapidApiClient rapidApiClient, final CountryStore countryStore)
    {
        super(statisticsStore, searchStore, searchResultStore);
        this.rapidApiClient = Defense.notNull(rapidApiClient, RapidApiClient.class.getSimpleName());
        this.countryStore = Defense.notNull(countryStore, CountryStore.class.getSimpleName());
    }

    @Override
    public SearchStatisticsContext create(final SearchStatisticsRequest request)
    {
        Optional<DbCountry> dbCountry = resolveCountry(request.getCountry());

        Optional<List<DbSearch>> existingOverlappingSearches = Optional.ofNullable(searchStore.getIfOverlapsBy(dbCountry, request.getDateFrom(), request.getDateTo()));

        GetHistoryResponse casesFrom = fetchHistoryForDate(resolveGetHistoryRequest(request.getCountry(), request.getDateFrom()));
        GetHistoryResponse casesTo   = fetchHistoryForDate(resolveGetHistoryRequest(request.getCountry(), request.getDateTo()));

        Mutable<DbStatistics> dbStatistics = new Mutable<>(DB_STATISTICS);

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
            public Mutable<DbStatistics> getDbStatistics()
            {
                return dbStatistics;
            }

            @Override
            public Optional<DbCountry> getCountry()
            {
                return dbCountry;
            }

            @Override
            public Optional<List<DbSearch>> getExistingSearches()
            {
                return existingOverlappingSearches;
            }

            @Override
            public SearchStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    private Optional<DbCountry> resolveCountry(final String country)
    {
        return Optional.ofNullable(countryStore.getRepository().getByName(country));
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
        try
        {
            return rapidApiClient.fetchHistory(request);
        }
        catch (RapidApiException e)
        {
            throw new AppException(ResponseCode.HTTP_CLIENT_EXCEPTION, e.getMessage());
        }
    }
}
