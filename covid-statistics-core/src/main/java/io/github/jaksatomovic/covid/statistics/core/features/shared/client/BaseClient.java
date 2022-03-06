package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

import io.github.jaksatomovic.covid.statistics.commons.utility.ApplicationResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.Executor;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class BaseClient
{
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected RestTemplate getRestTemplate()
    {
        return new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    }

    protected <T> HttpEntity<T> createHttpRequest(T body, HttpHeaders headers)
    {
        return new HttpEntity(body, headers);
    }

    abstract HttpHeaders fillHeader();

    protected <I extends RapidApiRequest, O extends RapidApiResponse> O secureExecute(Executor<I, O> executor)
        throws RapidApiException
    {
        try
        {
            return executor.execute(executor.getRequest());
        }
        catch (Exception var5)
        {
            throw this.handleGeneralException(var5, executor.getRequest());
        }
    }

    protected <T extends RapidApiRequest> RapidApiException handleGeneralException(Exception ex, T request)
        throws RapidApiException
    {
        logger.error("General exception occurred: {}", ex.getMessage());
        throw RapidApiException.createFrom(request, ApplicationResponseCode.UNKNOWN, ex.getMessage());
    }
}
