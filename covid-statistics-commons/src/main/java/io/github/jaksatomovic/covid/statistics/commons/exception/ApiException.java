package io.github.jaksatomovic.covid.statistics.commons.exception;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.commons.utility.ApplicationResponseCode;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class ApiException
    extends Exception
{
    public static ApiException createFrom(final String message)
    {
        return createFrom(ApplicationResponseCode.UNKNOWN, message);
    }

    public static ApiException createFrom(ApplicationResponseCode code, String message)
    {
        if (code == null)
        {
            throw new IllegalArgumentException("responseCode must not be null!");
        }
        else
        {
            return createFrom(message);
        }
    }

    public static <T extends AbstractRequest> ApiException createFrom(T request, ApplicationResponseCode code, String message)
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
            return createFrom(message);
        }
    }
}
