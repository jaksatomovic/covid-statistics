package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesResponse;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;
import io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update.UpdateCountriesContext;
import io.github.jaksatomovic.covid.statistics.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

@Service
public class SearchStatisticsResponseGenerator
    implements ResponseGenerator<SearchStatisticsResponse, SearchStatisticsContext>
{
    @Override
    public SearchStatisticsResponse generateResponse(final SearchStatisticsContext context)
    {

        // TODO
        return new SearchStatisticsResponse(context.getOriginalRequest(), ResponseCode.OK, new Statistics());
    }
}
