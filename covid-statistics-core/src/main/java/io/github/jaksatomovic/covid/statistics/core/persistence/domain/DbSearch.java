package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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

    @OneToMany (fetch = FetchType.EAGER,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                mappedBy = "search")
    private Set<DbSearchResult> searchResults;

    @OneToMany (fetch = FetchType.EAGER,
                cascade = CascadeType.ALL,
                orphanRemoval = true,
                mappedBy = "search")
    private Set<DbStatistics> statistics;

    /**
     * Gets search results.
     *
     * @return the search results
     */
    public Set<DbSearchResult> getSearchResults()
    {
        return searchResults;
    }

    /**
     * Sets search results.
     *
     * @param searchResults the search results
     */
    public void setSearchResults(final Set<DbSearchResult> searchResults)
    {
        this.searchResults = searchResults;
    }

    /**
     * Gets statistics.
     *
     * @return the statistics
     */
    public Set<DbStatistics> getStatistics()
    {
        return statistics;
    }

    /**
     * Sets statistics.
     *
     * @param statistics the statistics
     */
    public void setStatistics(final Set<DbStatistics> statistics)
    {
        this.statistics = statistics;
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
}
