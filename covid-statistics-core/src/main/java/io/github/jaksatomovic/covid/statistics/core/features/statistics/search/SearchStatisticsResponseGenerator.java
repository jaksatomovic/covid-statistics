package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type Search statistics response generator.
 */
@Service
public class SearchStatisticsResponseGenerator
    implements ResponseGenerator<SearchStatisticsResponse, SearchStatisticsContext>
{
    @Override
    public SearchStatisticsResponse generateResponse(final SearchStatisticsContext context)
    {
        return new SearchStatisticsResponse(context.getOriginalRequest(), ResponseCode.OK, resolveStatistics(context));
    }

    private Statistics resolveStatistics(final SearchStatisticsContext context)
    {
        Statistics statistics = new Statistics();
        statistics.setActiveCases(context.getDbStatistics().getValue().getActiveCases());
        statistics.setNewCases(context.getDbStatistics().getValue().getNewCases());
        statistics.setRecoveredCases(context.getDbStatistics().getValue().getRecoveredCases());
        statistics.setCriticalCases(context.getDbStatistics().getValue().getCriticalCases());
        return statistics;
    }
}
