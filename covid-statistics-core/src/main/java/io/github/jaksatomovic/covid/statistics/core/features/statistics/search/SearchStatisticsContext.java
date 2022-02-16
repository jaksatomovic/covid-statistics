package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContext;

import java.util.Optional;

/**
 * The interface Search statistics context.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface SearchStatisticsContext
    extends StatisticsContext<SearchStatisticsRequest>
{
    Optional<GetHistoryResponse> getCasesFrom();

    Optional<GetHistoryResponse> getCasesTo();
}
