package io.github.jaksatomovic.covid.statistics.api.shared;

import io.github.jaksatomovic.commons.api.model.ApiEntity;

/**
 * The type Statistics.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class Statistics
    extends ApiEntity
{
    private Integer newCases;
    private Integer activeCases;
    private Integer criticalCases;
    private Integer recoveredCases;

    /**
     * Gets new cases.
     *
     * @return the new cases
     */
    public Integer getNewCases()
    {
        return newCases;
    }

    /**
     * Sets new cases.
     *
     * @param newCases the new cases
     */
    public void setNewCases(final Integer newCases)
    {
        this.newCases = newCases;
    }

    /**
     * Gets active cases.
     *
     * @return the active cases
     */
    public Integer getActiveCases()
    {
        return activeCases;
    }

    /**
     * Sets active cases.
     *
     * @param activeCases the active cases
     */
    public void setActiveCases(final Integer activeCases)
    {
        this.activeCases = activeCases;
    }

    /**
     * Gets critical cases.
     *
     * @return the critical cases
     */
    public Integer getCriticalCases()
    {
        return criticalCases;
    }

    /**
     * Sets critical cases.
     *
     * @param criticalCases the critical cases
     */
    public void setCriticalCases(final Integer criticalCases)
    {
        this.criticalCases = criticalCases;
    }

    /**
     * Gets recovered cases.
     *
     * @return the recovered cases
     */
    public Integer getRecoveredCases()
    {
        return recoveredCases;
    }

    /**
     * Sets recovered cases.
     *
     * @param recoveredCases the recovered cases
     */
    public void setRecoveredCases(final Integer recoveredCases)
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
