package io.github.jaksatomovic.covid.statistics.commons.api.messages.request;


/**
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class ApiRequest
    extends AbstractRequest
{
    private static final long serialVersionUID = 1L;

    protected ApiRequest()
    {
    }

    protected static Long getVersion()
    {
        return serialVersionUID;
    }

    @Override
    public String getCorrelationId()
    {
        return super.getCorrelationId();
    }
}