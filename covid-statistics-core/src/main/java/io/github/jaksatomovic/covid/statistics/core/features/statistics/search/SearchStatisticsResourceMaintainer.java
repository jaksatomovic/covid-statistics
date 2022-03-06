package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Check;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiHandler;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearchResult;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Here we check context for existing searches. If there are any we need to take date_to for each
 * of them and fetch data for that day. If there are none we process new data normally.
 * <p>
 * Since there are so many edge cases with date overlapping I only took usecase when new search
 * is partly overlapping meaning I only check for date_to values in old searches
 * ****A1 |------------|A2  -> Old search
 * B1|--------------------|B2 -> Old search
 * *************C1|-------------|C2 -> New search
 * <p>
 * With this case above dates A2 and B2 are relevant, so we fetch data for those date in order to
 * create new search result for following dates: A2-C2 nad B2-C2. Results are then processed and saved to db
 * with search A and B and C pointing to search_result C
 */
@Service
public class SearchStatisticsResourceMaintainer
    implements ResourceMaintainer<SearchStatisticsContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SearchStore       searchStore;
    private final StatisticsStore   statisticsStore;
    private final SearchResultStore searchResultStore;
    private final RapidApiHandler   rapidApiHandler;

    /**
     * Instantiates a new Search statistics resource maintainer.
     *
     * @param searchStore       the search store
     * @param statisticsStore   the statistics store
     * @param searchResultStore the search result store
     * @param rapidApiHandler   the rapid api handler
     */
    public SearchStatisticsResourceMaintainer(final SearchStore searchStore, final StatisticsStore statisticsStore, final SearchResultStore searchResultStore, final RapidApiHandler rapidApiHandler)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
        this.statisticsStore = Defense.notNull(statisticsStore, StatisticsStore.class.getSimpleName());
        this.searchResultStore = Defense.notNull(searchResultStore, SearchResultStore.class.getSimpleName());
        this.rapidApiHandler = Defense.notNull(rapidApiHandler, RapidApiHandler.class.getSimpleName());
    }

    @Override
    public void resolve(final SearchStatisticsContext context)
    {
        DbSearch newSearch = handleSearch(context);

        if (context.getExistingSearches().isPresent() && Check.notEmpty(context.getExistingSearches().get()))
        {
            handleExistingData(context, newSearch);
        }
        else
        {
            handleNewSearch(context, newSearch);
        }

        logger.debug("[MAINTAINER] - Search Statistics - [OK]");
    }

    private void handleExistingData(final SearchStatisticsContext context, final DbSearch newSearch)
    {
        handleExistingSearch(newSearch, context);
        updateExistingSearchResults(newSearch, context);
    }

    private void handleExistingSearch(final DbSearch newSearch, final SearchStatisticsContext context)
    {
        for (DbSearch existingSearch : context.getExistingSearches().get())
        {
            handleRapidApiSearchForExistingData(existingSearch, context);
            handleNewData(context, newSearch);
        }
    }

    private void updateExistingSearchResults(final DbSearch newSearch, final SearchStatisticsContext context)
    {
        for (DbSearch existingSearch : context.getExistingSearches().get())
        {
            for (DbSearchResult dbSearchResult : existingSearch.getSearchResults())
            {
                dbSearchResult.setSearch(newSearch);
                searchResultStore.saveEntity(dbSearchResult);
            }
        }
    }

    private void handleNewSearch(final SearchStatisticsContext context, final DbSearch dbSearch)
    {
        handleRapidApiSearchForNewData(dbSearch, context);
        handleNewData(context, dbSearch);
    }

    private void handleNewData(final SearchStatisticsContext context, final DbSearch dbSearch)
    {
        handleSearchResults(context, dbSearch);
        handleStatistics(context, dbSearch);
    }

    private void handleRapidApiSearchForExistingData(final DbSearch existingSearch, final SearchStatisticsContext context)
    {
        GetHistoryResponse casesFrom = rapidApiHandler.fetchHistoryForDate(resolveGetHistoryRequest(context.getCountry()
                                                                                                           .get()
                                                                                                           .getName()
                                                                                                           .toLowerCase(), existingSearch.getDateTo()));
        GetHistoryResponse casesTo = rapidApiHandler.fetchHistoryForDate(resolveGetHistoryRequest(context.getCountry().get().getName().toLowerCase(), context.getOriginalRequest()
                                                                                                                                                             .getDateTo()));

        handleResponse(context, casesFrom, casesTo);
    }

    private void handleRapidApiSearchForNewData(final DbSearch newSearch, final SearchStatisticsContext context)
    {
        GetHistoryResponse casesFrom = rapidApiHandler.fetchHistoryForDate(resolveGetHistoryRequest(context.getCountry().get().getName().toLowerCase(), newSearch.getDateFrom()));
        GetHistoryResponse casesTo   = rapidApiHandler.fetchHistoryForDate(resolveGetHistoryRequest(context.getCountry().get().getName().toLowerCase(), newSearch.getDateTo()));

        handleResponse(context, casesFrom, casesTo);
    }

    private void handleResponse(final SearchStatisticsContext context, final GetHistoryResponse casesFrom, final GetHistoryResponse casesTo)
    {
        validateResponse(casesFrom, casesTo);

        context.getCasesFrom().setValue(casesFrom);
        context.getCasesTo().setValue(casesTo);
    }

    private void validateResponse(final GetHistoryResponse casesFrom, final GetHistoryResponse casesTo)
    {
        Defense.notNull(casesFrom, "casesFrom");
        Defense.notNull(casesTo, "casesTo");
        Defense.notNull(casesFrom.getResponse(), "casesFrom response");
        Defense.notNull(casesTo.getResponse(), "casesTo response");
        Defense.notEmpty(casesFrom.getResponse(), "response");
        Defense.notEmpty(casesTo.getResponse(), "response");
    }

    private DbSearch handleSearch(final SearchStatisticsContext context)
    {
        DbSearch dbSearch = new DbSearch();
        dbSearch.setCountry(context.getCountry().get());
        dbSearch.setDateFrom(context.getOriginalRequest().getDateFrom());
        dbSearch.setDateTo(context.getOriginalRequest().getDateTo());

        searchStore.saveEntity(dbSearch);

        logger.debug("[MAINTAINER] - DbSearch - [OK]");

        return dbSearch;
    }

    private void handleSearchResults(final SearchStatisticsContext context, final DbSearch dbSearch)
    {
        DbSearchResult dbSearchResult = new DbSearchResult();

        GetHistoryResponse.Cases casesFrom = context.getCasesFrom().getValue().getResponse().iterator().next().getCases();
        GetHistoryResponse.Cases casesTo   = context.getCasesTo().getValue().getResponse().iterator().next().getCases();

        dbSearchResult.setSearch(dbSearch);
        dbSearchResult.setActiveCases(calculateDifference(casesFrom.getActive(), casesTo.getActive()));
        dbSearchResult.setNewCases(calculateDifference(casesFrom.getNewCases(), casesTo.getNewCases()));
        dbSearchResult.setRecoveredCases(calculateDifference(casesFrom.getRecovered(), casesTo.getRecovered()));
        dbSearchResult.setCriticalCases(calculateDifference(casesFrom.getCritical(), casesTo.getCritical()));

        searchResultStore.saveEntity(dbSearchResult);

        logger.debug("[MAINTAINER] - DbSearchResult - [OK]");
    }

    private void handleStatistics(final SearchStatisticsContext context, final DbSearch dbSearch)
    {
        DbStatistics dbStatistics = new DbStatistics();

        GetHistoryResponse.Cases casesFrom = context.getCasesFrom().getValue().getResponse().iterator().next().getCases();
        GetHistoryResponse.Cases casesTo   = context.getCasesTo().getValue().getResponse().iterator().next().getCases();

        dbStatistics.setSearch(dbSearch);
        dbStatistics.setActiveCases(calculatePercentage(casesFrom.getActive(), casesTo.getActive()));
        dbStatistics.setNewCases(calculatePercentage(casesFrom.getNewCases(), casesTo.getNewCases()));
        dbStatistics.setRecoveredCases(calculatePercentage(casesFrom.getRecovered(), casesTo.getRecovered()));
        dbStatistics.setCriticalCases(calculatePercentage(casesFrom.getCritical(), casesTo.getCritical()));

        context.getDbStatistics().setValue(statisticsStore.saveEntity(dbStatistics));

        logger.debug("[MAINTAINER] - DbSearchResult - [OK]");
    }

    private Double calculatePercentage(final String startValue, final String finalValue)
    {
        return calculatePercentage(Double.parseDouble(startValue), Double.parseDouble(finalValue));
    }

    private Double calculatePercentage(final double startValue, final double finalValue)
    {
        return ((finalValue - startValue) / Math.abs(startValue)) * 100;
    }

    private Long calculateDifference(final String startValue, final String finalValue)
    {
        return calculateDifference(Long.parseLong(startValue), Long.parseLong(finalValue));
    }

    private Long calculateDifference(final long startValue, final long finalValue)
    {
        return finalValue - startValue;
    }

    private GetHistoryRequest resolveGetHistoryRequest(final String country, final LocalDate date)
    {
        GetHistoryRequest getHistoryRequest = new GetHistoryRequest();
        getHistoryRequest.setCountry(country);
        getHistoryRequest.setDate(date);

        return getHistoryRequest;
    }
}
