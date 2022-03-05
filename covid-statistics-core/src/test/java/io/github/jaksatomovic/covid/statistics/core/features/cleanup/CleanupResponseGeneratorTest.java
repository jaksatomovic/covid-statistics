package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
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
        CleanupResponseGenerator.class,
    }
)
class CleanupResponseGeneratorTest
    extends AbstractTest
{

    @Autowired
    private CleanupResponseGenerator generator;

    @Test
    void generateResponse()
    {
        CleanupResponse response = generator.generateResponse(getCleanupContext());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getResponseCode());
        Assertions.assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}