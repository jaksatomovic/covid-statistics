package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;

import java.util.List;
import java.util.Optional;

/**
 * The interface Search statistics context.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface DeleteStatisticsContext
    extends StatisticsContext<DeleteStatisticsRequest>
{
    Optional<List<DbSearch>> getExistingSearches();

}
