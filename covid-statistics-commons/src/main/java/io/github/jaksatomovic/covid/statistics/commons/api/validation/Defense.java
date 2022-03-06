package io.github.jaksatomovic.covid.statistics.commons.api.validation;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class Defense
{
    private static final String PARAMETER_LITERAL = "parameter";
    private static final String THRESHOLD_LITERAL = "threshold";
    private static final String COMPARATOR_INSTANT = "comparator instant";
    private static final String THRESHOLD_INSTANT = "threshold instant";
    private static final String PARAMETER_MUST_NOT_BE_NULL = "Parameter must not be null [parameter=%s]";
    private static final String PARAMETER_MUST_NOT_BE_EMPTY = "Parameter must not be empty [parameter=%s]";
    private static final String PARAMETER_MUST_BE_OF_REQUIRED_TYPE = "Parameter must be of required type [parameter=%s, actualType=%s, requiredType=%s]";

    private Defense() {
    }

    public static boolean assertTrue(boolean parameter, String name) {
        if (!parameter) {
            throwException(String.format("Indicator must not be false [parameter=%s]", name));
        }

        return parameter;
    }

    public static boolean assertFalse(boolean parameter, String name) {
        if (parameter) {
            throwException(String.format("Indicator must not be true [parameter=%s]", name));
        }

        return parameter;
    }

    public static <T> T notNull(T parameter, String name) {
        if (Check.isNull(parameter)) {
            throwException(String.format("Parameter must not be null [parameter=%s]", name));
        }

        return parameter;
    }

    public static String notEmpty(String parameter, String name) {
        if (Check.isEmpty(parameter)) {
            throwException(String.format("Parameter must not be empty [parameter=%s]", name));
        }

        return parameter;
    }

    public static <T> T[] notEmpty(T[] parameter, String name) {
        if (Check.isEmpty(parameter)) {
            throwException(String.format("Parameter must not be empty [parameter=%s]", name));
        }

        return parameter;
    }

    public static <T> Stream<T> asStream(Collection<T> collection) {
        return ((Collection)Optional.ofNullable(collection).orElse(Collections.emptySet())).stream();
    }

    public static <C extends Collection<?>> C notEmpty(C parameter, String name) {
        if (Check.isEmpty(parameter)) {
            throwException(String.format("Parameter must not be empty [parameter=%s]", name));
        }

        return parameter;
    }

    public static <M extends Map<?, ?>> M notEmpty(M parameter, String name) {
        if (Check.isEmpty(parameter)) {
            throwException(String.format("Parameter must not be empty [parameter=%s]", name));
        }

        return parameter;
    }

    public static String notBlank(String parameter, String name) {
        if (Check.isBlank(parameter)) {
            throwException(String.format("Parameter must not be empty [parameter=%s]", name));
        }

        return parameter;
    }

    public static <T> Class<T> ofType(Class<?> actualType, Class<T> requiredType, String name) {
        if (Check.notOfType(actualType, requiredType)) {
            throwException(String.format("Parameter must be of required type [parameter=%s, actualType=%s, requiredType=%s]", name, actualType, requiredType));
        }

        return requiredType;
    }

    public static <T> Class<T> ofType(Object parameter, Class<T> requiredType, String name) {
        if (Check.notOfType(parameter, requiredType)) {
            throwException(String.format("Parameter must be of required type [parameter=%s, actualType=%s, requiredType=%s]", name, parameter.getClass(), requiredType));
        }

        return requiredType;
    }

    public static String matches(String parameter, String expression, String name) {
        if (Check.isNull(parameter) || Check.isEmpty(expression) || !parameter.matches(expression)) {
            throwException(String.format("Parameter must match the regular expression [parameter=%s, value=%s, expression=%s]", name, parameter, expression));
        }

        return parameter;
    }

    public static <N extends Number> N notNegative(N number, String name) {
        if (Check.isNegative((Number)notNull(number, "number"))) {
            throwException(String.format("Parameter must not be negative [parameter=%s, value=%s]", name, number));
        }

        return number;
    }

    public static <N extends Number> N notPositive(N number, String name) {
        if (Check.isPositive((Number)notNull(number, "number"))) {
            throwException(String.format("Parameter must not be positive [parameter=%s, value=%s]", name, number));
        }

        return number;
    }

    public static <C extends Comparable<? super C>> C notLessThan(C parameter, C threshold, String name) {
        if (Check.isLessThan((Comparable)notNull(parameter, "parameter"), (Comparable)notNull(threshold, "threshold"))) {
            throwException(String.format("Parameter must not be less than the threshold [parameter=%s, value=%s, threshold=%s]", name, parameter, threshold));
        }

        return parameter;
    }

    public static <C extends Comparable<? super C>> C notLessOrEqualTo(C parameter, C threshold, String name) {
        if (Check.isLessOrEqualTo((Comparable)notNull(parameter, "parameter"), (Comparable)notNull(threshold, "threshold"))) {
            throwException(String.format("Parameter must not be less or equal to the threshold [parameter=%s, value=%s, threshold=%s]", name, parameter, threshold));
        }

        return parameter;
    }

    public static <C extends Comparable<? super C>> C isEqualTo(C comparator, C comparable, String name) {
        if (!Check.isEqualTo(comparator, comparable)) {
            throwException(String.format("Parameter must not be equals to the comparable one [parameter=%s, comparator=%s, comparable=%s]", name, comparator, comparable));
        }

        return comparator;
    }

    public static <C extends Comparable<? super C>> C notEqualTo(C comparator, C comparable, String name) {
        if (Check.isEqualTo(comparator, comparable)) {
            throwException(String.format("Parameter must not be equals to the comparable one [parameter=%s, comparator=%s, comparable=%s]", name, comparator, comparable));
        }

        return comparator;
    }

    public static <C extends Comparable<? super C>> C notGreaterOrEqualTo(C parameter, C threshold, String name) {
        if (Check.isGreaterOrEqualTo((Comparable)notNull(parameter, "parameter"), (Comparable)notNull(threshold, "threshold"))) {
            throwException(String.format("Parameter must not be greater or equal to the threshold [parameter=%s, value=%s, threshold=%s]", name, parameter, threshold));
        }

        return parameter;
    }

    public static <C extends Comparable<? super C>> C notGreaterThan(C parameter, C threshold, String name) {
        if (Check.isGreaterThan((Comparable)notNull(parameter, "parameter"), (Comparable)notNull(threshold, "threshold"))) {
            throwException(String.format("Parameter must not be greater than the threshold [parameter=%s, value=%s, threshold=%s]", name, parameter, threshold));
        }

        return parameter;
    }

    public static <C extends Comparable<? super C>> C within(C parameter, C lowerBound, C upperBound, String name) {
        notNull(parameter, name);
        notNull(lowerBound, "lower bound");
        notNull(upperBound, "upper bound");
        notGreaterOrEqualTo(lowerBound, upperBound, "lower bound to upper bound");
        if (Check.notWithin(parameter, lowerBound, upperBound)) {
            throwException("Parameter must be within the bounds [parameter=%s, value=%s, lowerBound=%s, upperBound=%s]", name, parameter, lowerBound, upperBound);
        }

        return parameter;
    }

    public static ZonedDateTime notBefore(ZonedDateTime comparator, ZonedDateTime threshold) {
        notNull(comparator, "comparator instant");
        notNull(threshold, "threshold instant");
        notBefore(comparator.toInstant(), threshold.toInstant());
        return comparator;
    }

    public static ZonedDateTime notAfter(ZonedDateTime comparator, ZonedDateTime threshold) {
        notNull(comparator, "comparator instant");
        notNull(threshold, "threshold instant");
        notAfter(comparator.toInstant(), threshold.toInstant());
        return comparator;
    }

    public static Instant notBefore(Instant comparator, Instant threshold) {
        notNull(comparator, "comparator instant");
        notNull(threshold, "threshold instant");
        if (comparator.isBefore(threshold)) {
            throwException("Instant must strictly be after the given threshold [comparator=%s, threshold=%s]", comparator, threshold);
        }

        return comparator;
    }

    public static Instant notAfter(Instant comparator, Instant threshold) {
        notNull(comparator, "comparator instant");
        notNull(threshold, "threshold instant");
        if (comparator.isAfter(threshold)) {
            throwException("Instant must strictly be before the given threshold [comparator=%s, threshold=%s]", comparator, threshold);
        }

        return comparator;
    }

    private static final void throwException(String message) {
        throw new IllegalArgumentException(message);
    }

    private static final void throwException(String template, Object... arguments) {
        throw new IllegalArgumentException(String.format(template, arguments));
    }
}
