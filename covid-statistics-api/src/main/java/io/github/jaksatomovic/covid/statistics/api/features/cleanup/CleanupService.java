package io.github.jaksatomovic.covid.statistics.api.features.cleanup;

import io.github.jaksatomovic.covid.statistics.commons.api.Api;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface CleanupService
    extends Api
{
    CleanupResponse delete(CleanupRequest request)
        throws ApiException;
}
