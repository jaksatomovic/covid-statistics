package io.github.jaksatomovic.covid.statistics.core.utility;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface TransactionalExecutor {
    <R> R transactional(PersistenceClosure<R> var1);

    <R> R transactional(Closure<R> var1);
}
