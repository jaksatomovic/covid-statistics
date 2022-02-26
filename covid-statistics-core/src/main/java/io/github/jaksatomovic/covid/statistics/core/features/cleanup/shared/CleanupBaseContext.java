package io.github.jaksatomovic.covid.statistics.core.features.cleanup.shared;

import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;

import java.util.List;
import java.util.Optional;

/**
 * The interface Statistics context.
 *
 * @param <R> the type parameter
 */
public interface CleanupBaseContext<R extends AbstractRequest>
    extends OperationContext<R>
{
    Optional<List<DbSearch>> getAllSearches();
}
