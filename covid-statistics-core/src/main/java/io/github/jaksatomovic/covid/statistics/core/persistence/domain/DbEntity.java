package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import io.github.jaksatomovic.commons.api.validation.Check;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Collection;

/**
 * The type Db entity.
 *
 * @param <K> the type parameter
 * @param <E> the type parameter
 *
 * @author Jaksa Tomovic
 * @since 1.0
 */
@MappedSuperclass
public abstract class DbEntity<K extends Serializable & Comparable<K>, E extends DbEntity<K, ? super E>> implements Serializable, Comparable<E>
{
    private static final long serialVersionUID = 1810L;

    /**
     * The constant ALLOCATION_SIZE.
     */
    public static final int ALLOCATION_SIZE = 1;

    /**
     * Instantiates the entity.<br>
     */
    protected DbEntity() {}

    /**
     * Sets the ID associated with this entity.<br>
     *
     * @param pk [&lt;K&gt;] :: the entity ID
     */
    public abstract void setId(K pk);

    /**
     * Returns the ID associated with this entity.<br>
     *
     * @return id [&lt;K&gt;] :: the entity ID
     */
    public abstract K getId();

    /**
     * Indicates if this database entity is a new one.<br>
     *
     * @return isNew [boolean] :: true, if this entity is a new one; false otherwise
     */
    public boolean isNew()
    {
        return Check.isNull(getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(E comparable)
    {
        if (Check.isNull(getId())) {
            return (Check.isNull(comparable.getId()) ? 0 : -1);
        }

        return getId().compareTo(comparable.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        if (Check.isNull(getId())) {
            return super.hashCode();
        }

        final int prime = 31;
        int result = 1;

        result = prime * result + getId().hashCode();

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object)
    {
        if (Check.isNull(getId())) {
            return super.equals(object);
        }

        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() == null) {
            return false;
        }

        if (!getClass().equals(object.getClass())) {
            return false;
        }

        return (compareTo((E)object) == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString()
    {
        final StringBuilder builder = new StringBuilder(getClass().getSimpleName()).append("::[").append("id=").append(getId());

        return toString(builder).append("]").toString();
    }

    /**
     * Appends the internal properties of this request to its {@link #toString()} output.<br>
     *
     * @param builder [{@link StringBuilder}] :: the builder to append the properties
     *
     * @return builder [{@link StringBuilder}] :: the builder with the appended properties
     */
    protected StringBuilder toString(StringBuilder builder)
    {
        return builder;
    }

    /**
     * Checks if the given entity is non-null and returns its primary key, if so.<br>
     * In case the entity is null the returned primary key is also null.<br>
     *
     * @param <C>    the type parameter
     * @param entity [{@link DbEntity}] :: the entity
     *
     * @return pk [&lt;I&gt;] :: the primary key value or null
     */
    protected final <C extends DbEntity<? extends K, ? super C>> K getId(C entity)
    {
        return (Check.notNull(entity) ? entity.getId() : null);
    }

    /**
     * Checks if the given collection is non-null and returns its size, if so.<br>
     * In case the collection is null the returned size is 0.<br>
     *
     * @param collection [{@link Collection}&lt;?&gt;] :: the collection
     *
     * @return size [int] :: the collection size or 0 in case the parameter is missing
     */
    protected final int getSize(Collection<?> collection)
    {
        return (Check.notEmpty(collection) ? collection.size() : 0);
    }

    /**
     * Returns the entity version.<br>
     *
     * @return version [long] :: the entity version
     */
    protected static long getSerialVersion()
    {
        return serialVersionUID;
    }
}