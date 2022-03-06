package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class SearchStatisticsPreconditionsValidator
    implements PreconditionsValidator<SearchStatisticsContext>
{
    @Override
    public void validate(final SearchStatisticsContext context)
    {
        Defense.notNull(context.getCountry().get(), "country");
    }
}

