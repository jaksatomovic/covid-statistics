package io.github.jaksatomovic.covid.statistics.api.features.cleanup;

import io.github.jaksatomovic.covid.statistics.commons.api.Api;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface CleanupService
    extends Api
{
    CleanupResponse delete(CleanupRequest request);
}
