package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        SearchStatisticsResponseGenerator.class,
    }
)
class SearchStatisticsResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private SearchStatisticsResponseGenerator generator;

    @Test
    void generateResponse()
    {
        SearchStatisticsResponse response = generator.generateResponse(getSearchStatisticsContext(prepareSearchStatisticsRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getPayload());
        Assertions.assertNotNull(response.getPayload().getActiveCases());
        Assertions.assertNotNull(response.getPayload().getCriticalCases());
        Assertions.assertNotNull(response.getPayload().getNewCases());
        Assertions.assertNotNull(response.getPayload().getRecoveredCases());
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}