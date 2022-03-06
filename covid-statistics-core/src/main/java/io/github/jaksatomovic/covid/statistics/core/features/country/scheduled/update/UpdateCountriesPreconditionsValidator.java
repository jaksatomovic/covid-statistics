package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountriesPreconditionsValidator
    implements PreconditionsValidator<UpdateCountriesContext>
{
    @Override
    public void validate(final UpdateCountriesContext context)
    {
        Defense.notNull(context.getExistingCountries().get(), "existing countries");
        Defense.notNull(context.getExternalCountries().get(), "existing countries");
    }
}

