package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
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
        CleanupPreconditionsValidator.class,
    }
)
class CleanupPreconditionsValidatorTest
    extends AbstractTest
{

    @Autowired
    private CleanupPreconditionsValidator validator;

    @Test
    void validate_OK()
    {
        validator.validate(getCleanupContext());
    }
}