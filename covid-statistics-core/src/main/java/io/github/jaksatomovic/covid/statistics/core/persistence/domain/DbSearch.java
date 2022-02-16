package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Db search.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Entity
@Table (name = DbSearch.TABLE_NAME)
@SequenceGenerator (name = DbSearch.SEQUENCE_NAME,
                    sequenceName = DbSearch.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbSearch
    extends DbEntity<Long, DbSearch>
{
    /**
     * The constant TABLE_NAME.
     */
    public static final  String TABLE_NAME       = "search";
    /**
     * The constant SEQUENCE_NAME.
     */
    public static final  String SEQUENCE_NAME    = "search_id_seq";
    private static final long   serialVersionUID = getSerialVersion();

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
                     generator = SEQUENCE_NAME)
    @Column (name = "id",
             nullable = false)
    private Long id;

    @Column (name = "country",
             nullable = false)
    private String country;

    @Column (name = "date_from",
             nullable = false)
    private LocalDate dateFrom;
    @Column (name = "date_to",
             nullable = false)
    private LocalDate dateTo;

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), id, country, dateFrom, dateTo);
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(final String country)
    {
        this.country = country;
    }

    /**
     * Gets date from.
     *
     * @return the date from
     */
    public LocalDate getDateFrom()
    {
        return dateFrom;
    }

    /**
     * Sets date from.
     *
     * @param dateFrom the date from
     */
    public void setDateFrom(final LocalDate dateFrom)
    {
        this.dateFrom = dateFrom;
    }

    /**
     * Gets date to.
     *
     * @return the date to
     */
    public LocalDate getDateTo()
    {
        return dateTo;
    }

    /**
     * Sets date to.
     *
     * @param dateTo the date to
     */
    public void setDateTo(final LocalDate dateTo)
    {
        this.dateTo = dateTo;
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
        DbSearch dbSearch = (DbSearch)o;
        return id.equals(dbSearch.id) && country.equals(dbSearch.country) && dateFrom.equals(dbSearch.dateFrom) && dateTo.equals(dbSearch.dateTo);
    }
}
