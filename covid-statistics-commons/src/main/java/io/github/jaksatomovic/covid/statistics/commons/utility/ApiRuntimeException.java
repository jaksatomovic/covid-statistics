package io.github.jaksatomovic.covid.statistics.commons.utility;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class ApiRuntimeException
    extends RuntimeException
{
    private static final long                    serialVersionUID = 1L;
    private final        ApplicationResponseCode code;

    public ApiRuntimeException(ApplicationResponseCode code)
    {
        this(code, (String)null);
    }

    public ApiRuntimeException(ApplicationResponseCode code, String message)
    {
        super(message);
        this.code = code;
    }

    public ApiRuntimeException(ApplicationResponseCode code, String format, Object... args)
    {
        super(String.format(format, args));
        this.code = code;
    }

    public ApplicationResponseCode getResponseCode()
    {
        return this.code;
    }
}
