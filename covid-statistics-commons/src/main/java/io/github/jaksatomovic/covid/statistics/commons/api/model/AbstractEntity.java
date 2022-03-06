package io.github.jaksatomovic.covid.statistics.commons.api.model;

import java.io.Serializable;

/**
 * The type Abstract entity.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public abstract class AbstractEntity
    implements Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Abstract entity.
     */
    protected AbstractEntity()
    {
    }

    /**
     * Append fields.
     *
     * @param sb the sb
     */
    protected abstract void appendFields(StringBuilder sb);

    public String toString()
    {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append('[');
        this.appendFields(sb);
        sb.append(']');
        return sb.toString();
    }
}
