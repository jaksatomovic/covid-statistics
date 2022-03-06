package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Delete statistics request validator.
 */
@Service
public class DeleteStatisticsRequestValidator
    implements RequestValidator<DeleteStatisticsRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new Search statistics request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public DeleteStatisticsRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final DeleteStatisticsRequest request)
    {
        baseRequestValidator.validate(request);
        Defense.notNull(request.getCountry(), "country");
        Defense.notNull(request.getDateFrom(), "date from");
        Defense.notNull(request.getDateTo(), "date to");
        logger.debug("[VALIDATION] - Update Countries Request - [OK]");
    }
}