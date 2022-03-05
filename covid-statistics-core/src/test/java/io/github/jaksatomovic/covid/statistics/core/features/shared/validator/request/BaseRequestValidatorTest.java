package io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
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
        BaseRequestValidator.class,
    }
)
class BaseRequestValidatorTest
{
    @Autowired
    private BaseRequestValidator validator;

    @Test
    void validate_OK()
    {
        validator.validate(new CleanupRequest());
    }
}