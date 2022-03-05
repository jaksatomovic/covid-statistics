package io.github.jaksatomovic.covid.statistics.core.features.shared.peer;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.core.features.cleanup.CleanupOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
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
        CleanupPeer.class,
    }
)
class CleanupPeerTest
{

    @Autowired
    private CleanupPeer peer;

    @MockBean
    private CleanupOperation cleanupOperation;

    @Test
    void delete()
    {
        Assertions.assertDoesNotThrow(() -> peer.delete(new CleanupRequest()));
    }
}