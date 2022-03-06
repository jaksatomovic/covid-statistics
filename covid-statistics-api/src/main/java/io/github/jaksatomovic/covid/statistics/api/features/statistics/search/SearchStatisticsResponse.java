package io.github.jaksatomovic.covid.statistics.api.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.response.AbstractPayloadResponse;

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
