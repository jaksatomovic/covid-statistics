package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
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
        Defense.notNull(context.getCasesFrom().get(), "response from");
        Defense.notNull(context.getCasesTo().get(), "response to");
        Defense.notNull(context.getCasesFrom().get().getResponse(), "cases from");
        Defense.notNull(context.getCasesTo().get().getResponse(), "cases to");
        Defense.notEmpty(context.getCasesFrom().get().getResponse(), "cases from");
        Defense.notEmpty(context.getCasesTo().get().getResponse(), "cases to");
    }
}

