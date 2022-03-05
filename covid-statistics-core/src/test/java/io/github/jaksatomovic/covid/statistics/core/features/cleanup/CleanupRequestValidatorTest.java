package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.BaseRequestValidator;
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
        CleanupRequestValidator.class,
    }
)
class CleanupRequestValidatorTest
{

    @Autowired
    private CleanupRequestValidator validator;

    @MockBean
    private BaseRequestValidator baseRequestValidator;

    @Test
    void validate_OK()
    {
        validator.validate(new CleanupRequest());
    }
}