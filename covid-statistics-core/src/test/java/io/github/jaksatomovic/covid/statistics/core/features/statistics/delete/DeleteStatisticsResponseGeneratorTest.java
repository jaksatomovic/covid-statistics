package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
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
        DeleteStatisticsResponseGenerator.class,
    }
)
class DeleteStatisticsResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private DeleteStatisticsResponseGenerator generator;

    @Test
    void generateResponse()
    {
        DeleteStatisticsResponse response = generator.generateResponse(getDeleteStatisticsContext(prepareDeleteStatisticsRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}