package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
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

@Service
public class SearchStatisticsResourceMaintainer
    implements ResourceMaintainer<SearchStatisticsContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SearchStore       searchStore;
    private final StatisticsStore   statisticsStore;
    private final SearchResultStore searchResultStore;

    public SearchStatisticsResourceMaintainer(final SearchStore searchStore, final StatisticsStore statisticsStore, final SearchResultStore searchResultStore)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
        this.statisticsStore = Defense.notNull(statisticsStore, StatisticsStore.class.getSimpleName());
        this.searchResultStore = Defense.notNull(searchResultStore, SearchResultStore.class.getSimpleName());
    }

    @Override
    public void resolve(final SearchStatisticsContext context)
    {
        DbSearch dbSearch = handleSearch(context);
        handleSearchResults(context, dbSearch);
        handleStatistics(context, dbSearch);

        logger.debug("[MAINTAINER] - Search Statistics - [OK]");
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

        GetHistoryResponse.Cases casesFrom = context.getCasesFrom().get().getResponse().iterator().next().getCases();
        GetHistoryResponse.Cases casesTo   = context.getCasesTo().get().getResponse().iterator().next().getCases();

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

        GetHistoryResponse.Cases casesFrom = context.getCasesFrom().get().getResponse().iterator().next().getCases();
        GetHistoryResponse.Cases casesTo   = context.getCasesTo().get().getResponse().iterator().next().getCases();

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
}
