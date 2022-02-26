package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.Mutable;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;

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

    Mutable<DbStatistics> getDbStatistics();
}
