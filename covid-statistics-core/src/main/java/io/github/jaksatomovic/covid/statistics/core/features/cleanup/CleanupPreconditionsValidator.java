package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.context.PreconditionsValidator;
import org.springframework.stereotype.Service;

@Service
public class CleanupPreconditionsValidator
    implements PreconditionsValidator<CleanupContext>
{
    @Override
    public void validate(final CleanupContext context)
    {
        Defense.notNull(context.getAllSearches().get(), "all database search entries");
    }
}

