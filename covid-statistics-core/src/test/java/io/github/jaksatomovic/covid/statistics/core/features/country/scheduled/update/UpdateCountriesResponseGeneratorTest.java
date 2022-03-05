package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesResponse;
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
        UpdateCountriesResponseGenerator.class,
    }
)
class UpdateCountriesResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private UpdateCountriesResponseGenerator generator;

    @Test
    void generateResponse()
    {
        UpdateCountriesResponse response = generator.generateResponse(getUpdateCountriesContext(new UpdateCountriesRequest()));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}