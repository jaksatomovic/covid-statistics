package io.github.jaksatomovic.covid.statistics.api.features.statistics.search;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.commons.api.messages.response.AbstractPayloadResponse;
import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;

/**
 * The type Search statistics response.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class SearchStatisticsResponse
    extends AbstractPayloadResponse<Statistics>
{
    public SearchStatisticsResponse(final AbstractRequest request, final ResponseCode code, final Statistics payload)
    {
        super(request, code, payload);
    }
}
