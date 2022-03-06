package io.github.jaksatomovic.covid.statistics.commons.api.validation;

import java.util.Collection;
import java.util.Map;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class Check
{
    private Check()
    {
    }

    public static <T> boolean isNull(T parameter)
    {
        return parameter == null;
    }

    public static <T> boolean notNull(T parameter)
    {
        return !isNull(parameter);
    }

    public static boolean isAnyNull(Object... parameters)
    {
        Object[] var1 = parameters;
        int      var2 = parameters.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Object parameter = var1[var3];
            if (isNull(parameter))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isAnyNotNull(Object... parameters)
    {
        Object[] var1 = parameters;
        int      var2 = parameters.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Object parameter = var1[var3];
            if (notNull(parameter))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isAnyNotEmpty(Collection<?>... collections)
    {
        Collection[] var1 = collections;
        int          var2 = collections.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Collection<?> collection = var1[var3];
            if (notEmpty(collection))
            {
                return true;
            }
        }

        return false;
    }

    public static boolean areAllNull(Object... parameters)
    {
        Object[] var1 = parameters;
        int      var2 = parameters.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Object parameter = var1[var3];
            if (notNull(parameter))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean areAllNotNull(Object... parameters)
    {
        Object[] var1 = parameters;
        int      var2 = parameters.length;

        for (int var3 = 0; var3 < var2; ++var3)
        {
            Object parameter = var1[var3];
            if (isNull(parameter))
            {
                return false;
            }
        }

        return true;
    }

    public static Boolean isEmpty(String parameter)
    {
        return isNull(parameter) || parameter.isEmpty();
    }

    public static boolean notEmpty(String parameter)
    {
        return !isEmpty(parameter);
    }

    public static boolean isAnyEmpty(String... strings)
    {
        if (isNull(strings))
        {
            return true;
        }
        else
        {
            String[] var1 = strings;
            int      var2 = strings.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                String string = var1[var3];
                if (isEmpty(string))
                {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean areAllEmpty(String... strings)
    {
        if (isNull(strings))
        {
            return true;
        }
        else
        {
            String[] var1 = strings;
            int      var2 = strings.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                String string = var1[var3];
                if (notEmpty(string))
                {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean areAllEmpty(Collection<?>... collections)
    {
        if (isNull(collections))
        {
            return true;
        }
        else
        {
            Collection[] var1 = collections;
            int          var2 = collections.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                Collection<?> collection = var1[var3];
                if (notEmpty(collection))
                {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean areNotEmpty(String... strings)
    {
        return !isAnyEmpty(strings);
    }

    public static boolean areAllNotEmpty(String... strings)
    {
        return !areAllEmpty(strings);
    }

    public static boolean areAllNotEmpty(Collection<?>... collections)
    {
        return !areAllEmpty(collections);
    }

    public static boolean isAnyBlank(String... strings)
    {
        if (isNull(strings))
        {
            return true;
        }
        else
        {
            String[] var1 = strings;
            int      var2 = strings.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                String string = var1[var3];
                if (isBlank(string))
                {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean areAllBlank(String... strings)
    {
        if (isNull(strings))
        {
            return true;
        }
        else
        {
            String[] var1 = strings;
            int      var2 = strings.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                String string = var1[var3];
                if (notBlank(string))
                {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean areAllNotBlank(String... strings)
    {
        return !isAnyBlank(strings);
    }

    public static <T> boolean isEmpty(T[] array)
    {
        return areAllNull(array);
    }

    public static <T> boolean notEmpty(T[] array)
    {
        return !isEmpty(array);
    }

    public static <C extends Collection<?>> boolean isEmpty(C collection)
    {
        return !isNull(collection) && collection.isEmpty();
    }

    public static <C extends Collection<?>> boolean notEmpty(C collection)
    {
        return !isEmpty(collection);
    }

    public static <M extends Map<?, ?>> boolean isEmpty(M map)
    {
        return isNull(map) || map.isEmpty();
    }

    public static <M extends Map<?, ?>> boolean notEmpty(M map)
    {
        return !isEmpty(map);
    }

    public static boolean isBlank(String parameter)
    {
        return isNull(parameter) || parameter.trim().isEmpty();
    }

    public static boolean notBlank(String parameter)
    {
        return !isBlank(parameter);
    }

    public static boolean isOfType(Object type, Class<?> requiredType)
    {
        return notNull(type) && isOfType(type.getClass(), requiredType);
    }

    public static boolean isOfType(Class<?> actualType, Class<?> requiredType)
    {
        return areAllNotNull(actualType, requiredType) && requiredType.isAssignableFrom(actualType);
    }

    public static boolean notOfType(Object actualType, Class<?> requiredType)
    {
        return !isOfType(actualType, requiredType);
    }

    public static boolean notOfType(Class<?> actualType, Class<?> requiredType)
    {
        return !isOfType(actualType, requiredType);
    }

    public static <T> boolean isEqualTo(T comparator, T comparable)
    {
        return areAllNull(comparator, comparable) || comparator.equals(comparable);
    }

    public static <T> boolean notEqualTo(T comparator, T comparable)
    {
        return !isEqualTo(comparator, comparable);
    }

    public static <N extends Number> boolean isZero(N number)
    {
        return notNull(number) && isEqualTo((Object)number, (int)0);
    }

    public static <N extends Number> boolean notZero(N number)
    {
        return !isZero(number);
    }

    public static <N extends Number> boolean isNegative(N number)
    {
        return notNull(number) && number.intValue() < 0;
    }

    public static <N extends Number> boolean notNegative(N number)
    {
        return !isNegative(number);
    }

    public static <N extends Number> boolean isPositive(N number)
    {
        return notNull(number) && number.intValue() > 0;
    }

    public static <N extends Number> boolean notPositive(N number)
    {
        return !isPositive(number);
    }

    public static <N extends Number> boolean isEven(N number)
    {
        return isEqualTo((Object)(number.doubleValue() % 2.0D), (int)0);
    }

    public static <N extends Number> boolean notEven(N number)
    {
        return !isEven(number);
    }

    public static <N extends Number> boolean isOdd(N number)
    {
        return !isEven(number);
    }

    public static <N extends Number> boolean notOdd(N number)
    {
        return isEven(number);
    }

    public static boolean and(Boolean... comparables)
    {
        if (notEmpty((Object[])comparables))
        {
            Boolean[] var1 = comparables;
            int       var2 = comparables.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                boolean comparable = var1[var3];
                if (!comparable)
                {
                    return false;
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean or(Boolean... comparables)
    {
        if (notEmpty((Object[])comparables))
        {
            Boolean[] var1 = comparables;
            int       var2 = comparables.length;

            for (int var3 = 0; var3 < var2; ++var3)
            {
                boolean comparable = var1[var3];
                if (comparable)
                {
                    return true;
                }
            }

            return false;
        }
        else
        {
            return true;
        }
    }

    public static <C extends Comparable<? super C>> boolean isLessThan(C comparable, C threshold)
    {
        if (isNull(comparable))
        {
            return true;
        }
        else
        {
            return comparable.compareTo(threshold) < 0;
        }
    }

    public static <C extends Comparable<? super C>> boolean isLessOrEqualTo(C comparable, C threshold)
    {
        if (isNull(comparable))
        {
            return true;
        }
        else
        {
            return comparable.compareTo(threshold) <= 0;
        }
    }

    public static <C extends Comparable<? super C>> boolean isEqualTo(C comparable, C threshold)
    {
        if (isNull(comparable))
        {
            return false;
        }
        else
        {
            return comparable.compareTo(threshold) == 0;
        }
    }

    public static <C extends Comparable<? super C>> boolean isGreaterOrEqualTo(C comparable, C threshold)
    {
        if (isNull(comparable))
        {
            return true;
        }
        else
        {
            return comparable.compareTo(threshold) >= 0;
        }
    }

    public static <C extends Comparable<? super C>> boolean isGreaterThan(C comparable, C threshold)
    {
        if (isNull(comparable))
        {
            return true;
        }
        else
        {
            return comparable.compareTo(threshold) > 0;
        }
    }

    public static <C extends Comparable<? super C>> boolean notLessThan(C comparable, C threshold)
    {
        return !isLessThan(comparable, threshold);
    }

    public static <C extends Comparable<? super C>> boolean notLessOrEqualTo(C comparable, C threshold)
    {
        return !isLessThan(comparable, threshold);
    }

    public static <C extends Comparable<? super C>> boolean notEqualTo(C comparable, C threshold)
    {
        return !isEqualTo(comparable, threshold);
    }

    public static <C extends Comparable<C>> boolean notGreaterOrEqualTo(C comparable, C threshold)
    {
        return !isGreaterThan(comparable, threshold);
    }

    public static <C extends Comparable<? super C>> boolean notGreaterThan(C comparable, C threshold)
    {
        return !isGreaterThan(comparable, threshold);
    }

    public static <C extends Comparable<? super C>> boolean isWithin(C comparable, C from, C to)
    {
        return isGreaterOrEqualTo(comparable, from) && isLessThan(comparable, to);
    }

    public static <C extends Comparable<? super C>> boolean notWithin(C comparable, C from, C to)
    {
        return !isWithin(comparable, from, to);
    }
}
