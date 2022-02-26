package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatisticsResourceMaintainer
    implements ResourceMaintainer<DeleteStatisticsContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SearchStore searchStore;

    public DeleteStatisticsResourceMaintainer(final SearchStore searchStore)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
    }

    @Override
    public void resolve(final DeleteStatisticsContext context)
    {
        searchStore.deleteEntities(context.getExistingSearches().get());

        logger.debug("[MAINTAINER] - Delete Statistics - [OK]");
    }
}
