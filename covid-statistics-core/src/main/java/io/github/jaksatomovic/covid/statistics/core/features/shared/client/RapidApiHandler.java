package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.commons.utility.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.exception.AppException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
import org.springframework.stereotype.Service;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class RapidApiHandler
{
    private final RapidApiClient client;

    public RapidApiHandler(final RapidApiClient client)
    {
        this.client = Defense.notNull(client, RapidApiClient.class.getSimpleName());
    }

    public GetHistoryResponse fetchHistoryForDate(final GetHistoryRequest request)
    {
        try
        {
            return client.fetchHistory(request);
        }
        catch (RapidApiException e)
        {
            throw new AppException(ResponseCode.HTTP_CLIENT_EXCEPTION, e.getMessage());
        }
    }
}
