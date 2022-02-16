package io.github.jaksatomovic.covid.statistics.core.features.shared.context;

import java.io.Serializable;
import java.util.Optional;

/**
 * Wrapper object for mutable context variables.
 * For example, sso manager id could be null at the start of certain operation,
 * but at later stage it could be filled.
 *
 * @param <T> the type parameter
 * @author Jaksa Tomovic
 * @since 1.0
 */
public class Mutable<T>
    implements Serializable
{
    private final String name;
    private       T      value;

    /**
     * Instantiates a new Mutable.
     *
     * @param name the name
     */
    public Mutable(final String name)
    {
        this.name = name;
    }

    /**
     * Instantiates a new Mutable.
     *
     * @param name  the name
     * @param value the value
     */
    public Mutable(final String name, final T value)
    {
        this.name = name;
        this.value = value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue()
    {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(final T value)
    {
        this.value = value;
    }

    /**
     * Return {@code true} if there is a value present, otherwise {@code false}.
     *
     * @return {@code true} if there is a value present, otherwise {@code false}
     */
    public boolean isPresent()
    {
        return value != null;
    }

    /**
     * To optional optional.
     *
     * @return the optional
     */
    public Optional<T> toOptional()
    {
        return Optional.ofNullable(value);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Mutable{");
        sb.append("name='").append(name).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
