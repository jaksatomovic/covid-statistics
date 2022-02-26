package io.github.jaksatomovic.covid.statistics.core.features.cleanup.shared;

import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.ContextCreator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Statistics context creator.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 */
public abstract class CleanupBaseContextCreator<C extends OperationContext<? extends AbstractRequest>, R extends AbstractRequest>
    implements ContextCreator<C, R>
{
    /**
     * The Search store.
     */
    protected final SearchStore searchStore;

    /**
     * Instantiates a new Cleanup base context creator.
     *
     * @param searchStore the search store
     */
    @Autowired
    protected CleanupBaseContextCreator(final SearchStore searchStore)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
    }
}
