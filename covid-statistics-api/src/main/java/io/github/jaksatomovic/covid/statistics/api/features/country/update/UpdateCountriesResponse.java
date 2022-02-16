package io.github.jaksatomovic.covid.statistics.api.features.country.update;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.commons.api.messages.response.AbstractResponse;

/**
 * The type Update country response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class UpdateCountriesResponse
    extends AbstractResponse
{
    /**
     * Instantiates a new Update country response.
     *
     * @param request      the request
     * @param responseCode the response code
     */
    public UpdateCountriesResponse(final AbstractRequest request, final ResponseCode responseCode)
    {
        super(request, responseCode);
    }
}
