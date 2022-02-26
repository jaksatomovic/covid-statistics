package io.github.jaksatomovic.covid.statistics.core.features.shared.peer;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupService;
import org.springframework.stereotype.Service;

/**
 * The type Cleanup peer.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class CleanupPeer
    implements CleanupService
{
    @Override
    public CleanupResponse delete(final CleanupRequest request)
    {
        return null;
    }
}
