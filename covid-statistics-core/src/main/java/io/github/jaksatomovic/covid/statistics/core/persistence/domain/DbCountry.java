package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

/**
 * The type Db country.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Entity
@Table (name = DbCountry.TABLE_NAME)
@SequenceGenerator (name = DbCountry.SEQUENCE_NAME,
                    sequenceName = DbCountry.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbCountry
    extends DbEntity<Long, DbCountry>
{
    /**
     * The constant TABLE_NAME.
     */
    public static final  String TABLE_NAME       = "country";
    /**
     * The constant SEQUENCE_NAME.
     */
    public static final  String SEQUENCE_NAME    = "country_id_seq";
    private static final long   serialVersionUID = getSerialVersion();

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
                     generator = SEQUENCE_NAME)
    @Column (name = "id",
             nullable = false)
    private Long id;

    @Column (name = "name",
             nullable = false)
    private String name;

    /**
     * Instantiates a new Db country.
     */
    public DbCountry()
    {
    }

    /**
     * Instantiates a new Db country.
     *
     * @param name the name
     */
    public DbCountry(final String name)
    {
        this.name = name;
    }

    /**
     * Instantiates a new Db country.
     *
     * @param id   the id
     * @param name the name
     */
    public DbCountry(final Long id, final String name)
    {
        this.id = id;
        this.name = name;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public Long getId()
    {
        return id;
    }

    @Override
    public void setId(final Long id)
    {
        this.id = id;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        if (!super.equals(o))
        {
            return false;
        }
        DbCountry dbCountry = (DbCountry)o;
        return id.equals(dbCountry.id) && name.equals(dbCountry.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, name);
    }
}
