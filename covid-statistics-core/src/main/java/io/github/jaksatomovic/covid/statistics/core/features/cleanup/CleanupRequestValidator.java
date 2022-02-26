package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Cleanup request validator.
 */
@Service
public class CleanupRequestValidator
    implements RequestValidator<CleanupRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new Cleanup request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public CleanupRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final CleanupRequest request)
    {
        baseRequestValidator.validate(request);
        logger.debug("[VALIDATION] - Cleanup Request - [OK]");
    }
}