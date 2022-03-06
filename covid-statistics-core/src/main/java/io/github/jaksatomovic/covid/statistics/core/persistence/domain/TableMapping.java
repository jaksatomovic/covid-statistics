package io.github.jaksatomovic.covid.statistics.core.persistence.domain;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;

/**
 * The interface Table mapping.
 *
 * @author Jaksa Tomovic
 * @since 1.0
 */
public interface TableMapping
{
    /**
     * Map table mapping.
     *
     * @param field  [{@link String}] :: the (mandatory) mapped field
     * @param column [{@link String}] :: the (mandatory) mapped column
     * @return mapping [{@link TableMapping}] :: the (mandatory) table mapping
     */
    static TableMapping map(final String field, final String column)
    {
        Defense.notBlank(field, "mapped database entity field");
        Defense.notBlank(column, "mapped database entity column");

        return new TableMapping()
        {
            /**
             * {@inheritDoc}
             */
            @Override
            public String getField()
            {
                return field;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String getColumn()
            {
                return column;
            }
        };
    }

    /**
     * Return the mapped field of the implementing entity instance.<br>
     *
     * @return field [{@link String}] :: the (mandatory) mapped field
     */
    String getField();

    /**
     * Returns the mapped SQL column of the schema.<br>
     *
     * @return column [{@link String}] :: the (mandatory) mapped column
     */
    String getColumn();
}