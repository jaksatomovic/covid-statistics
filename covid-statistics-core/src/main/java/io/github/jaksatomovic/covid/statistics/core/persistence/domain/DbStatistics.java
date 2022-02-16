package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
