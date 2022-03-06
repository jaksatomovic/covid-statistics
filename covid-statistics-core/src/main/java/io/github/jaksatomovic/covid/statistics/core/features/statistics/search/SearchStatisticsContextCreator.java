package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
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

    private final CountryStore countryStore;

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     * @param countryStore      the country store
     */
    protected SearchStatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore, final CountryStore countryStore)
    {
        super(statisticsStore, searchStore, searchResultStore);
        this.countryStore = Defense.notNull(countryStore, CountryStore.class.getSimpleName());
    }

    @Override
    public SearchStatisticsContext create(final SearchStatisticsRequest request)
    {
        Optional<DbCountry> dbCountry = resolveCountry(request.getCountry());

        Optional<List<DbSearch>> existingOverlappingSearches = Optional.ofNullable(searchStore.getIfOverlapsBy(dbCountry, request.getDateFrom(), request.getDateTo()));

        Mutable<DbStatistics>       dbStatistics = new Mutable<>(DB_STATISTICS);
        Mutable<GetHistoryResponse> casesFrom    = new Mutable<>("casesFrom");
        Mutable<GetHistoryResponse> casesTo      = new Mutable<>("casesTo");

        return new SearchStatisticsContext()
        {
            @Override
            public Mutable<GetHistoryResponse> getCasesFrom()
            {
                return casesFrom;
            }

            @Override
            public Mutable<GetHistoryResponse> getCasesTo()
            {
                return casesTo;
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
}
