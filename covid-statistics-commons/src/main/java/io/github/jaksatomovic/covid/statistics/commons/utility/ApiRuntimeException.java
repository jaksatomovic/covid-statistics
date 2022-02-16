package io.github.jaksatomovic.covid.statistics.commons.utility;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class ApiRuntimeException
    extends RuntimeException
{
    private static final long         serialVersionUID = 1L;
    private final        ResponseCode code;

    public ApiRuntimeException(ResponseCode code)
    {
        this(code, (String)null);
    }

    public ApiRuntimeException(ResponseCode code, String message)
    {
        super(message);
        this.code = code;
    }

    public ApiRuntimeException(ResponseCode code, String format, Object... args)
    {
        super(String.format(format, args));
        this.code = code;
    }

    public ResponseCode getResponseCode()
    {
        return this.code;
    }
}
