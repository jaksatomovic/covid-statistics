package io.github.jaksatomovic.covid.statistics.api.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.response.AbstractResponse;

/**
 * The type Update country response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class DeleteStatisticsResponse
    extends AbstractResponse
{
    /**
     * Instantiates a new Update country response.
     *
     * @param request      the request
     * @param responseCode the response code
     */
    public DeleteStatisticsResponse(final AbstractRequest request, final ResponseCode responseCode)
    {
        super(request, responseCode);
    }
}
