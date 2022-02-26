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
@Table (name = DbStatistics.TABLE_NAME)
@SequenceGenerator (name = DbStatistics.SEQUENCE_NAME,
                    sequenceName = DbStatistics.SEQUENCE_NAME,
                    allocationSize = DbEntity.ALLOCATION_SIZE)
public class DbStatistics
    extends DbEntity<Long, DbStatistics>
{

    public static final  String TABLE_NAME       = "statistics";
    public static final  String SEQUENCE_NAME    = "statistics_id_seq";
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
    private Double newCases;

    @Column (name = "active_cases",
             nullable = false)
    private Double activeCases;

    @Column (name = "critical_cases",
             nullable = false)
    private Double criticalCases;

    @Column (name = "recovered_cases",
             nullable = false)
    private Double recoveredCases;

    public DbSearch getSearch()
    {
        return search;
    }

    public void setSearch(final DbSearch search)
    {
        this.search = search;
    }

    public Double getNewCases()
    {
        return newCases;
    }

    public void setNewCases(final Double newCases)
    {
        this.newCases = newCases;
    }

    public Double getActiveCases()
    {
        return activeCases;
    }

    public void setActiveCases(final Double activeCases)
    {
        this.activeCases = activeCases;
    }

    public Double getCriticalCases()
    {
        return criticalCases;
    }

    public void setCriticalCases(final Double criticalCases)
    {
        this.criticalCases = criticalCases;
    }

    public Double getRecoveredCases()
    {
        return recoveredCases;
    }

    public void setRecoveredCases(final Double recoveredCases)
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
