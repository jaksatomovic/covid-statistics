package io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception;

import io.github.jaksatomovic.covid.statistics.commons.utility.ApiRuntimeException;
import io.github.jaksatomovic.covid.statistics.commons.utility.ApplicationResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiRequest;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class RapidApiException
    extends ApiRuntimeException
{
    public RapidApiException(final ApplicationResponseCode code, final String message)
    {
        super(code, message);
    }

    public static RapidApiException createFrom(RapidApiRequest request, ApplicationResponseCode code, String message)
    {
        if (request == null)
        {
            throw new IllegalArgumentException("request must not be null!");
        }
        else if (code == null)
        {
            throw new IllegalArgumentException("responseCode must not be null!");
        }
        else
        {
            return new RapidApiException(ApplicationResponseCode.UNKNOWN, message);
        }
    }
}
