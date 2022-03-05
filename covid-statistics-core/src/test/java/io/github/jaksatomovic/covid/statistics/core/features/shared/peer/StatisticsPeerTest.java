package io.github.jaksatomovic.covid.statistics.core.features.shared.peer;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.delete.DeleteStatisticsOperation;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.search.SearchStatisticsOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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
        StatisticsPeer.class,
    }
)
class StatisticsPeerTest
{

    @Autowired
    private StatisticsPeer peer;

    @MockBean
    private SearchStatisticsOperation searchStatisticsOperation;
    @MockBean
    private DeleteStatisticsOperation deleteStatisticsOperation;

    @Test
    void searchBy()
    {
        Assertions.assertDoesNotThrow(() -> peer.searchBy(new SearchStatisticsRequest()));
    }

    @Test
    void deleteBy()
    {
        Assertions.assertDoesNotThrow(() -> peer.deleteBy(new DeleteStatisticsRequest()));
    }
}