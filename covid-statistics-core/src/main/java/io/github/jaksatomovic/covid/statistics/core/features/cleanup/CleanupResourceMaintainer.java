package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.commons.api.validation.Check;
import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Cleanup resource maintainer.
 */
@Service
public class CleanupResourceMaintainer
    implements ResourceMaintainer<CleanupContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SearchStore searchStore;

    /**
     * Instantiates a new Cleanup resource maintainer.
     *
     * @param searchStore the search store
     */
    public CleanupResourceMaintainer(final SearchStore searchStore)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
    }

    @Override
    public void resolve(final CleanupContext context)
    {
        if (Check.notEmpty(context.getAllSearches().get()))
        {
            searchStore.deleteAllEntities();
        }

        logger.debug("[MAINTAINER] - Cleanup - [OK]");
    }
}
