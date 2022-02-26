package io.github.jaksatomovic.covid.statistics.core.features.statistics.shared;

import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.ContextCreator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The type Statistics context creator.
 *
 * @param <C> the type parameter
 * @param <R> the type parameter
 */
public abstract class StatisticsContextCreator<C extends OperationContext<? extends AbstractRequest>, R extends AbstractRequest>
    implements ContextCreator<C, R>
{
    /**
     * The Statistics store.
     */
    protected final StatisticsStore   statisticsStore;
    /**
     * The Search store.
     */
    protected final SearchStore       searchStore;
    /**
     * The Search result store.
     */
    protected final SearchResultStore searchResultStore;

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     */
    @Autowired
    protected StatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore)
    {
        this.statisticsStore = Defense.notNull(statisticsStore, StatisticsStore.class.getSimpleName());
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
        this.searchResultStore = Defense.notNull(searchResultStore, SearchResultStore.class.getSimpleName());
    }
}
