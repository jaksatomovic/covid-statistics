package io.github.jaksatomovic.covid.statistics.core.features.statistics.shared;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;

import java.util.List;
import java.util.Optional;

/**
 * The interface Statistics context.
 *
 * @param <R> the type parameter
 */
public interface StatisticsContext<R extends AbstractRequest>
    extends OperationContext<R>
{
    Optional<DbCountry> getCountry();

    Optional<List<DbSearch>> getExistingSearches();
}
