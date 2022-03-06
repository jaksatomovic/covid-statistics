package io.github.jaksatomovic.covid.statistics.commons.utility;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class ApplicationResponseCode
{
    public static final ApplicationResponseCode OK                    = successfulCode("OK", 0, "OK");
    public static final ApplicationResponseCode IN_PROGRESS           = successfulCode("IN_PROGRESS", -1, "in progress");
    public static final ApplicationResponseCode ACCESS_DENIED         = businessErrorCode("ACCESS_DENIED", 1000000001, "access denied");
    public static final ApplicationResponseCode UNSUPPORTED_OPERATION = otherErrorCode("UNSUPPORTED_OPERATION", 1000000002, "operation is not supported");
    public static final ApplicationResponseCode REQUEST_INVALID       = businessErrorCode("REQUEST_INVALID", 1000000003, "request is invalid");
    public static final ApplicationResponseCode ENTITY_NOT_FOUND      = businessErrorCode("ENTITY_NOT_FOUND", 1000000004, "entity not found");
    public static final ApplicationResponseCode ENTITY_ALREADY_EXISTS = businessErrorCode("ENTITY_ALREADY_EXISTS", 1000000005, "entity already exists");
    public static final ApplicationResponseCode PERSISTENCE_EXCEPTION = businessErrorCode("PERSISTENCE_EXCEPTION", 1000000006, "persistence exception");
    public static final ApplicationResponseCode HTTP_CLIENT_EXCEPTION = businessErrorCode("HTTP_CLIENT_EXCEPTION", 1000000007, "http client exception");
    public static final ApplicationResponseCode UNKNOWN               = otherErrorCode("UNKNOWN", 1000000000, "unknown response");
    private final       String                  name;
    private final       ResponseCodeCategory    category;
    private final       Integer                 code;
    private final       String                  description;

    public ApplicationResponseCode(String name, ResponseCodeCategory category, Integer code, String description)
    {
        this.name = name;
        this.code = code;
        this.category = category;
        this.description = description;
    }

    public static ApplicationResponseCode technicalErrorCode(String name, Integer code, String description)
    {
        return new ApplicationResponseCode(name, ResponseCodeCategory.TECHNICAL_ERROR, code, description);
    }

    public static ApplicationResponseCode businessErrorCode(String name, Integer code, String description)
    {
        return new ApplicationResponseCode(name, ResponseCodeCategory.BUSINESS_ERROR, code, description);
    }

    public static ApplicationResponseCode otherErrorCode(String name, Integer code, String description)
    {
        return new ApplicationResponseCode(name, ResponseCodeCategory.OTHER, code, description);
    }

    public static ApplicationResponseCode successfulCode(String name, Integer code, String description)
    {
        return new ApplicationResponseCode(name, ResponseCodeCategory.SUCCESSFUL, code, description);
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Integer getCode()
    {
        return this.code;
    }

    public ResponseCodeCategory getCategory()
    {
        return this.category;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("[name='").append(this.name).append('\'');
        sb.append(", category=").append(this.category);
        sb.append(", code=").append(this.code);
        sb.append(']');
        return sb.toString();
    }
}
