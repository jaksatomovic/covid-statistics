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
