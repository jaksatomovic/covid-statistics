package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountriesRequestValidator
    implements RequestValidator<UpdateCountriesRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    public UpdateCountriesRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final UpdateCountriesRequest request)
    {
        baseRequestValidator.validate(request);
        logger.debug("[VALIDATION] - Update Countries Request - [OK]");
    }
}