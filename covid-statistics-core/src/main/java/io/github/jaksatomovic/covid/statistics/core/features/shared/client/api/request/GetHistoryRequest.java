package io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request;

import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiRequest;

import java.time.LocalDate;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class GetHistoryRequest
    extends RapidApiRequest
{
    private String    country;
    private LocalDate date;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(final String country)
    {
        this.country = country;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(final LocalDate date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("GetHistoryRequest{");
        sb.append("country='").append(country).append('\'');
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
