package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.BaseRequestValidator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.request.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Search statistics request validator.
 */
@Service
public class SearchStatisticsRequestValidator
    implements RequestValidator<SearchStatisticsRequest>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BaseRequestValidator baseRequestValidator;

    /**
     * Instantiates a new Search statistics request validator.
     *
     * @param baseRequestValidator the base request validator
     */
    public SearchStatisticsRequestValidator(final BaseRequestValidator baseRequestValidator)
    {
        this.baseRequestValidator = Defense.notNull(baseRequestValidator, BaseRequestValidator.class.getSimpleName());
    }

    @Override
    public void validate(final SearchStatisticsRequest request)
    {
        baseRequestValidator.validate(request);
        Defense.notNull(request.getCountry(), "country");
        Defense.notNull(request.getDateFrom(), "date from");
        Defense.notNull(request.getDateTo(), "date to");
        logger.debug("[VALIDATION] - Update Countries Request - [OK]");
    }
}