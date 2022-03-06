package io.github.jaksatomovic.covid.statistics.commons.api.messages.response;

import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class AbstractPayloadResponse<T>
    extends AbstractResponse
{

    private T payload;

    public AbstractPayloadResponse(AbstractRequest request, ResponseCode code, T payload)
    {
        super(request, code);
        this.payload = payload;
    }

    public void populateFrom(AbstractPayloadResponse<T> response)
    {
        if (response != null)
        {
            this.populateFrom(response);
            this.setPayload(response.getPayload());
        }

    }

    public T getPayload()
    {
        return this.payload;
    }

    public void setPayload(T payload)
    {
        this.payload = payload;
    }

    @Override
    protected void appendFields(StringBuilder sb)
    {
        super.appendFields(sb);
        sb.append(", payload=");
        sb.append(this.payload);
    }
}

