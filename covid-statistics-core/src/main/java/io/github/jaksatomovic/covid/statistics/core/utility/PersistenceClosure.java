package io.github.jaksatomovic.covid.statistics.core.utility;

import javax.persistence.EntityManager;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface PersistenceClosure<R> {
    R execute(EntityManager var1);
}
