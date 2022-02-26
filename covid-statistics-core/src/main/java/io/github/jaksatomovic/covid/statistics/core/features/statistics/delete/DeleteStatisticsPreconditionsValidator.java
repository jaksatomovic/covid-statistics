package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.context.PreconditionsValidator;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.search.SearchStatisticsContext;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatisticsPreconditionsValidator
    implements PreconditionsValidator<DeleteStatisticsContext>
{
    @Override
    public void validate(final DeleteStatisticsContext context)
    {
        Defense.notNull(context.getExistingSearches().get(), "existing searches");
    }
}

