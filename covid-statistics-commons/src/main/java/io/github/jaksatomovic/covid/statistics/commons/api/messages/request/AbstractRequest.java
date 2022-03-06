package io.github.jaksatomovic.covid.statistics.commons.api.messages.request;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.AbstractMessage;

import java.util.Map;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class AbstractRequest
    extends AbstractMessage
{
    public AbstractRequest()
    {
        this((String)null);
    }

    public AbstractRequest(String correlationId)
    {
        super(correlationId);
    }

    public AbstractRequest(String correlationId, Map<String, String> transparentData)
    {
        super(correlationId, transparentData);
    }
}
