package io.github.jaksatomovic.covid.statistics.api.features.statistics;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface StatisticsService
{
    SearchStatisticsResponse searchBy(SearchStatisticsRequest request)
        throws ApiException;

    DeleteStatisticsResponse deleteBy(DeleteStatisticsRequest request)
        throws ApiException;
}
