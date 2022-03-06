package io.github.jaksatomovic.covid.statistics.core.features.shared.context;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;

/**
 * Base for every operation context class.
 * The idea is to have a dedicated context on which the certain operation works.
 * Context is used to store all the data that is first retrieved from the DB or external systems (there could be some optional ones),
 * and then validated later in the operation flow.
 *
 * @param <R> Original request stored in every context.
 * @author Jaksa Tomovic
 */
public interface OperationContext<R extends AbstractRequest>
{
    R getOriginalRequest();
}
