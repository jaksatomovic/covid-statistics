package io.github.jaksatomovic.covid.statistics.core.exception;

import io.github.jaksatomovic.covid.statistics.commons.utility.ApiRuntimeException;
import io.github.jaksatomovic.covid.statistics.commons.utility.ApplicationResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
public class AppException
    extends ApiRuntimeException
{
    public AppException(final ApplicationResponseCode code, final String message)
    {
        super(code, message);
    }

    public AppException(ApplicationResponseCode applicationResponseCode)
    {
        super(applicationResponseCode);
    }
}
