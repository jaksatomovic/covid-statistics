package io.github.jaksatomovic.covid.statistics.commons.api.messages;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class AbstractMessage
    implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String              correlationId;
    private Map<String, String> transparentData;

    protected AbstractMessage(String correlationId)
    {
        this.correlationId = correlationId;
    }

    public AbstractMessage(String correlationId, Map<String, String> transparentData)
    {
        this.correlationId = correlationId;
        this.transparentData = transparentData;
    }

    protected AbstractMessage(AbstractMessage message)
    {
        this((String)null);
        this.populateFrom(message);
    }

    public void populateFrom(AbstractMessage message)
    {
        if (message != null)
        {
            this.setCorrelationId(message.getCorrelationId());
            this.setTransparentData(message.getTransparentData());
        }
    }

    public String getCorrelationId()
    {
        return this.correlationId;
    }

    public void setCorrelationId(String correlationId)
    {
        this.correlationId = correlationId;
    }

    public Map<String, String> getTransparentData()
    {
        return this.transparentData;
    }

    public void setTransparentData(Map<String, String> transparentData)
    {
        this.transparentData = transparentData;
    }

    protected abstract void appendFields(StringBuilder sb);

    public final String toString()
    {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        this.appendFields(sb);
        sb.append(", correlationId=").append(this.correlationId);
        sb.append(", transparentData=").append(this.transparentData);
        sb.append(']');
        return sb.toString();
    }
}
