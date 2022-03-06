package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiHandler;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        SearchStatisticsResourceMaintainer.class
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
    private RapidApiHandler   rapidApiHandler;

    @Test
    void resolve()
    {
        when(rapidApiHandler.fetchHistoryForDate(ArgumentMatchers.any())).thenReturn(prepareGetHistoryResponse());

        Assertions.assertDoesNotThrow(() -> maintainer.resolve(getSearchStatisticsContext(prepareSearchStatisticsRequest())));
    }

    private GetHistoryResponse prepareGetHistoryResponse()
    {
        GetHistoryResponse                getHistoryResponse = new GetHistoryResponse();
        List<GetHistoryResponse.Response> responses          = new ArrayList<>();
        GetHistoryResponse.Response       response           = new GetHistoryResponse.Response();

        GetHistoryResponse.Cases cases = new GetHistoryResponse.Cases();

        cases.setNewCases("100");
        cases.setActive(100);
        cases.setCritical(100);
        cases.setRecovered(100);
        cases.setOneMPopulation("100");
        cases.setTotal(100);

        response.setCases(cases);

        responses.set(0, response);

        getHistoryResponse.setResponse(responses);
        return getHistoryResponse;
    }
}