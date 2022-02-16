package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.covid.statistics.core.persistence.repository.CountryRepository;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SearchStatisticsResourceMaintainer
    implements ResourceMaintainer<SearchStatisticsContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SearchStore searchStore;

    public SearchStatisticsResourceMaintainer(final SearchStore searchStore)
    {
        this.searchStore = Defense.notNull(searchStore, SearchStore.class.getSimpleName());
    }

    @Override
    public void resolve(final SearchStatisticsContext context)
    {

        logger.debug("[MAINTAINER] - Search Statistics - [OK]");
    }
}
