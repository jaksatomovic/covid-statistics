package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
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
        CleanupResourceMaintainer.class,
    }
)
class CleanupResourceMaintainerTest
    extends AbstractTest
{

    @Autowired
    private CleanupResourceMaintainer maintainer;

    @MockBean
    private SearchStore searchStore;

    @Test
    void resolve()
    {
        Assertions.assertDoesNotThrow(() -> maintainer.resolve(getCleanupContext()));
    }
}