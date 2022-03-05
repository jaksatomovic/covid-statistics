package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

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
        DeleteStatisticsPreconditionsValidator.class,
    }
)
class DeleteStatisticsPreconditionsValidatorTest
    extends AbstractTest
{

    @Autowired
    private DeleteStatisticsPreconditionsValidator validator;

    @Test
    void validate()
    {
        validator.validate(getDeleteStatisticsContext(prepareDeleteStatisticsRequest()));
    }
}