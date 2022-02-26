package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Entity
@Table (name = DbSearchResult.TABLE_NAME)
@SequenceGenerator (name = DbSearchResult.SEQUENCE_NAME,
                    sequenceName = DbSearchResult.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbSearchResult
    extends DbEntity<Long, DbSearchResult>
{

    public static final  String TABLE_NAME       = "search_result";
    public static final  String SEQUENCE_NAME    = "search_result_id_seq";
    private static final long   serialVersionUID = getSerialVersion();

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE,
                     generator = SEQUENCE_NAME)
    @Column (name = "id",
             nullable = false)
    private Long id;

    @ManyToOne
    private DbSearch search;

    @Column (name = "new_cases",
             nullable = false)
    private Long newCases;

    @Column (name = "active_cases",
             nullable = false)
    private Long activeCases;

    @Column (name = "critical_cases",
             nullable = false)
    private Long criticalCases;

    @Column (name = "recovered_cases",
             nullable = false)
    private Long recoveredCases;

    public DbSearch getSearch()
    {
        return search;
    }

    public void setSearch(final DbSearch search)
    {
        this.search = search;
    }

    public Long getNewCases()
    {
        return newCases;
    }

    public void setNewCases(final Long newCases)
    {
        this.newCases = newCases;
    }

    public Long getActiveCases()
    {
        return activeCases;
    }

    public void setActiveCases(final Long activeCases)
    {
        this.activeCases = activeCases;
    }

    public Long getCriticalCases()
    {
        return criticalCases;
    }

    public void setCriticalCases(final Long criticalCases)
    {
        this.criticalCases = criticalCases;
    }

    public Long getRecoveredCases()
    {
        return recoveredCases;
    }

    public void setRecoveredCases(final Long recoveredCases)
    {
        this.recoveredCases = recoveredCases;
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
