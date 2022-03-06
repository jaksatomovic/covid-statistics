package io.github.jaksatomovic.covid.statistics.commons.utility;

/**
 * The type Covid Statistics service exception.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class CovidStatisticsServiceException
    extends ApiRuntimeException
{

    /**
     * Creates the exception by the given arguments.<br>
     *
     * @param responseCode [{@link ApplicationResponseCode}] :: the response code
     * @param message      [String] :: the message
     */
    public CovidStatisticsServiceException(ApplicationResponseCode responseCode, String message)
    {
        super(responseCode, message);
    }

    /**
     * Creates a <b>database</b> exception.<br>
     *
     * @param template  [{@link String}] :: the error message template
     * @param arguments [{@link Object}[]] :: the error template arguments
     * @return exception [{@link ApplicationResponseCode}] :: the exception
     */
    public static CovidStatisticsServiceException databaseError(String template, Object... arguments)
    {
        return error(ApplicationResponseCode.PERSISTENCE_EXCEPTION, template, arguments);
    }

    /**
     * Returns an <b>unknown error</b> exception.<br>
     *
     * @param message   [String] :: the error message template
     * @param arguments [Object[]] :: the error template arguments
     * @return exception [{@link CovidStatisticsServiceException}] :: the exception
     */
    public static CovidStatisticsServiceException unknownError(String message, Object... arguments)
    {
        return error(ApplicationResponseCode.UNKNOWN, message, arguments);
    }

    /**
     * Returns an <b>internal error</b> exception.<br>
     *
     * @param message   [String] :: the error message template
     * @param arguments [Object[]] :: the error template arguments
     * @return exception [{@link CovidStatisticsServiceException}] :: the exception
     */
    public static CovidStatisticsServiceException internalError(String message, Object... arguments)
    {
        return error(ApplicationResponseCode.UNKNOWN, message, arguments);
    }

    /**
     * Creates a <b>io</b> exception.<br>
     *
     * @param message   the message
     * @param arguments the arguments
     * @return exception [{@link CovidStatisticsServiceException}] :: the exception
     */
    public static CovidStatisticsServiceException ioError(String message, Object... arguments)
    {
        return error(ApplicationResponseCode.HTTP_CLIENT_EXCEPTION, message, arguments);
    }

    /**
     * Creates a <b>validation error</b> exception.<br>
     *
     * @param message   [String] :: the error message template
     * @param arguments [Object[]] :: the error template arguments
     * @return exception [{@link CovidStatisticsServiceException}] :: the exception
     */
    public static CovidStatisticsServiceException validationError(String message, Object... arguments)
    {
        return error(ApplicationResponseCode.REQUEST_INVALID, message, arguments);
    }

    /**
     * Creates a <b>missing parameter</b> exception.<br>“
     *
     * @param name [String] :: the missing parameter name
     * @return exception [{@link CovidStatisticsServiceException}] :: the exception
     */
    public static CovidStatisticsServiceException missingParameter(String name)
    {
        return internalError("Missing mandatory property [name=%s]", name);
    }

    /**
     * Creates a new top-up service exception by the given arguments.<br>
     *
     * @param responseCode [{@link ApplicationResponseCode}] :: the response code
     * @param template     [String] :: the error message template
     * @param arguments    [Object[]] :: the error message template arguments
     * @return exception [{@link CovidStatisticsServiceException}] :: SSOServiceException
     */
    private static CovidStatisticsServiceException error(ApplicationResponseCode responseCode, String template, Object... arguments)
    {
        return new CovidStatisticsServiceException(responseCode, String.format(template, arguments));
    }

    @Override
    public final String toString()
    {
        return String.format("%s::[responseCode=%s, message=%s]", getClass().getSimpleName(), getResponseCode(), getMessage());
    }
}