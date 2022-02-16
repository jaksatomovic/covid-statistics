package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.core.features.country.shared.context.CountryContext;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.Mutable;

import java.util.Map;
import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public interface UpdateCountriesContext
    extends CountryContext<UpdateCountriesRequest>
{
    String IS_OBSOLETE = "is_obsolete";

    Optional<Map<String, Long>> getExternalCountries();

    Mutable<Boolean> isObsolete();
}
