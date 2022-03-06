package io.github.jaksatomovic.covid.statistics.commons.api.model;

/**
 * The type Api entity.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class ApiEntity
    extends AbstractEntity
{
    private static final long serialVersionUID = getApiVersion();

    /**
     * Instantiates a new Api entity.
     */
    protected ApiEntity()
    {
    }

    /**
     * Gets api version.
     *
     * @return the api version
     */
    protected static Long getApiVersion()
    {
        return serialVersionUID;
    }
}
