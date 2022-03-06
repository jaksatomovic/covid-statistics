package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

@Service
public class UpdateCountriesResponseGenerator
    implements ResponseGenerator<UpdateCountriesResponse, UpdateCountriesContext>
{
    @Override
    public UpdateCountriesResponse generateResponse(final UpdateCountriesContext context)
    {
        return new UpdateCountriesResponse(context.getOriginalRequest(), ResponseCode.OK);
    }
}
