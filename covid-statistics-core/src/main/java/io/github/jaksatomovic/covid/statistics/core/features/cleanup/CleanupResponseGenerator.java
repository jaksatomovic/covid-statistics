package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type Cleanup response generator.
 */
@Service
public class CleanupResponseGenerator
    implements ResponseGenerator<CleanupResponse, CleanupContext>
{
    @Override
    public CleanupResponse generateResponse(final CleanupContext context)
    {
        return new CleanupResponse(context.getOriginalRequest(), ResponseCode.OK);
    }
}
