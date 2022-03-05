package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiClient;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        SearchStatisticsResourceMaintainer.class,
        RapidApiClient.class,
        SearchResultStore.class,
        SearchStore.class,
        StatisticsStore.class
    }
)
class SearchStatisticsResourceMaintainerTest
    extends AbstractTest
{

    @Autowired
    private SearchStatisticsResourceMaintainer maintainer;

    @MockBean
    private SearchStore       searchStore;
    @MockBean
    private StatisticsStore   statisticsStore;
    @MockBean
    private SearchResultStore searchResultStore;
    @MockBean
    private RapidApiClient    rapidApiClient;

    @Test
    void resolve()
    {
        Mockito.when(rapidApiClient.fetchHistory(ArgumentMatchers.any())).thenReturn(prepareGetHistoryResponse());

        Assertions.assertDoesNotThrow(() -> maintainer.resolve(getSearchStatisticsContext(prepareSearchStatisticsRequest())));
    }
}