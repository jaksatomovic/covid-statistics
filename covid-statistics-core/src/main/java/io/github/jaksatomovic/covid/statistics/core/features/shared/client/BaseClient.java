package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

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
}
