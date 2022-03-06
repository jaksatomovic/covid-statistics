package io.github.jaksatomovic.covid.statistics.api.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;

import java.time.LocalDate;

/**
 * The type Search statistics request.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class DeleteStatisticsRequest
    extends AbstractRequest
{
    private String    country;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public String getCountry()
    {
        return country;
    }

    public void setCountry(final String country)
    {
        this.country = country;
    }

    public LocalDate getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(final LocalDate dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo()
    {
        return dateTo;
    }

    public void setDateTo(final LocalDate dateTo)
    {
        this.dateTo = dateTo;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {
        sb.append(", country=").append(country);
        sb.append(", dateFrom=").append(dateFrom);
        sb.append(", dateTo=").append(dateTo);
    }
}
