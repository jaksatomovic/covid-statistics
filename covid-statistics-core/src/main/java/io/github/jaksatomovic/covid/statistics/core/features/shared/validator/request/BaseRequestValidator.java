package io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Base Request Validator.
 *
 * @author Jaksa Tomovic
 * @since 1.0
 */
@Service
public class BaseRequestValidator
    implements RequestValidator<AbstractRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void validate(final AbstractRequest request)
    {
        Defense.notNull(request, "request");
        logger.trace("[VALIDATION] - Base Request - [OK]");
    }
}
