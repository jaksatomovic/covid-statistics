package io.github.jaksatomovic.covid.statistics.api.shared;

import io.github.jaksatomovic.covid.statistics.commons.api.model.ApiEntity;

/**
 * The type Statistics.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class Statistics
    extends ApiEntity
{
    private Double newCases;
    private Double activeCases;
    private Double criticalCases;
    private Double recoveredCases;

    public Statistics()
    {
    }

    public Statistics(final Double newCases, final Double activeCases, final Double criticalCases, final Double recoveredCases)
    {
        this.newCases = newCases;
        this.activeCases = activeCases;
        this.criticalCases = criticalCases;
        this.recoveredCases = recoveredCases;
    }

    /**
     * Gets new cases.
     *
     * @return the new cases
     */
    public Double getNewCases()
    {
        return newCases;
    }

    /**
     * Sets new cases.
     *
     * @param newCases the new cases
     */
    public void setNewCases(final Double newCases)
    {
        this.newCases = newCases;
    }

    /**
     * Gets active cases.
     *
     * @return the active cases
     */
    public Double getActiveCases()
    {
        return activeCases;
    }

    /**
     * Sets active cases.
     *
     * @param activeCases the active cases
     */
    public void setActiveCases(final Double activeCases)
    {
        this.activeCases = activeCases;
    }

    /**
     * Gets critical cases.
     *
     * @return the critical cases
     */
    public Double getCriticalCases()
    {
        return criticalCases;
    }

    /**
     * Sets critical cases.
     *
     * @param criticalCases the critical cases
     */
    public void setCriticalCases(final Double criticalCases)
    {
        this.criticalCases = criticalCases;
    }

    /**
     * Gets recovered cases.
     *
     * @return the recovered cases
     */
    public Double getRecoveredCases()
    {
        return recoveredCases;
    }

    /**
     * Sets recovered cases.
     *
     * @param recoveredCases the recovered cases
     */
    public void setRecoveredCases(final Double recoveredCases)
    {
        this.recoveredCases = recoveredCases;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {
        sb.append(", newCases=").append(newCases);
        sb.append(", activeCases=").append(activeCases);
        sb.append(", criticalCases=").append(criticalCases);
        sb.append(", recoveredCases=").append(recoveredCases);
    }
}
