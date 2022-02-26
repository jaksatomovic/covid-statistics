package io.github.jaksatomovic.covid.statistics.core.features.shared.peer;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupService;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;
import io.github.jaksatomovic.covid.statistics.core.features.cleanup.CleanupOperation;
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
    private final CleanupOperation operation;

    /**
     * Instantiates a new Cleanup peer.
     *
     * @param operation the operation
     */
    public CleanupPeer(final CleanupOperation operation)
    {
        this.operation = Defense.notNull(operation, CleanupOperation.class.getSimpleName());
    }

    @Override
    public CleanupResponse delete(final CleanupRequest request)
        throws ApiException
    {
        return operation.execute(request);
    }
}
