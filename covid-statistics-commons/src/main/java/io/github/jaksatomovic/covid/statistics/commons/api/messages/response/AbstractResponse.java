package io.github.jaksatomovic.covid.statistics.commons.api.messages.response;

import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.AbstractMessage;
import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class AbstractResponse
    extends AbstractMessage
{
    private ResponseCode responseCode;
    private String       responseDetail;

    protected AbstractResponse(AbstractRequest request, ResponseCode responseCode)
    {
        super(request);
        this.setResponseCode(responseCode);
    }

    public void populateFrom(AbstractResponse response)
    {
        if (response != null)
        {
            this.populateFrom(response);
            this.setResponseCode(response.getResponseCode());
        }

    }

    public ResponseCode getResponseCode()
    {
        return this.responseCode;
    }

    public void setResponseCode(ResponseCode responseCode)
    {
        this.responseCode = responseCode;
    }

    public String getResponseDetail()
    {
        return this.responseDetail;
    }

    public void setResponseDetail(String responseDetail)
    {
        this.responseDetail = responseDetail;
    }

    protected void appendFields(StringBuilder sb)
    {
        sb.append(", responseCode=").append(this.responseCode);
        sb.append(", responseDetail=").append(this.responseDetail);
    }
}
