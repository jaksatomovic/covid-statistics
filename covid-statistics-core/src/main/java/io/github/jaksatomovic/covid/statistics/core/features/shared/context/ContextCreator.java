package io.github.jaksatomovic.covid.statistics.core.features.shared.context;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;

/**
 * Base interface for all context creator classes.
 * The idea is to have a dedicated context on which the certain operation works.
 * Based on provided request, all the needed data is retrieved from the DB, calculated or manipulated which results in
 * new context object which will then be used in the next phases of the operation flow.
 *
 * @param <C> Type of context that is created.
 * @param <R> Request type - context creator will use the data from request to create operation context.
 * @author Jaksa Tomovic
 */
public interface ContextCreator<C extends OperationContext, R extends AbstractRequest>
{
    C create(R request)
        throws ApiException;
}
