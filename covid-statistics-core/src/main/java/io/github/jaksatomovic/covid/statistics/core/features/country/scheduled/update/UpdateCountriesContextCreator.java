package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.commons.api.validation.Check;
import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.commons.utility.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.exception.AppException;
import io.github.jaksatomovic.covid.statistics.core.features.country.shared.context.CountryContextCreator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.RapidApiClient;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetCountriesRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetCountriesResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.Mutable;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.CountryStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class UpdateCountriesContextCreator
    extends CountryContextCreator<UpdateCountriesContext, UpdateCountriesRequest>
{
    private final RapidApiClient rapidApiClient;

    /**
     * Instantiates a new Update countries context creator.
     *
     * @param countryStore   the country store
     * @param rapidApiClient the rapid api client
     */
    public UpdateCountriesContextCreator(final CountryStore countryStore, final RapidApiClient rapidApiClient)
    {
        super(countryStore);
        this.rapidApiClient = Defense.notNull(rapidApiClient, RapidApiClient.class.getSimpleName());
    }

    @Override
    public UpdateCountriesContext create(final UpdateCountriesRequest request)
    {
        Optional<Map<String, Long>> existingCountries = countryStore.fetchAllCountries();

        Map<String, Long> externalCountries = fetchAllCountriesFromApi();

        Mutable<Boolean> isObsolete = resolveIsObsolete(externalCountries, existingCountries);

        return new UpdateCountriesContext()
        {
            @Override
            public Optional<Map<String, Long>> getExternalCountries()
            {
                return Optional.ofNullable(externalCountries);
            }

            @Override
            public Mutable<Boolean> isObsolete()
            {
                return isObsolete;
            }

            @Override
            public UpdateCountriesRequest getOriginalRequest()
            {
                return request;
            }

            @Override
            public Optional<Map<String, Long>> getExistingCountries()
            {
                return existingCountries;
            }
        };
    }

    private Mutable<Boolean> resolveIsObsolete(final Map<String, Long> externalCountries, final Optional<Map<String, Long>> existingCountries)
    {
        if (existingCountries.isPresent())
        {
            for (Map.Entry<String, Long> externalCountry : externalCountries.entrySet())
            {
                Long value = existingCountries.get().putIfAbsent(externalCountry.getKey(), externalCountry.getValue());

                if (Check.isNull(value))
                {
                    return new Mutable<>(UpdateCountriesContext.IS_OBSOLETE, true);
                }
            }
        }

        return new Mutable<>(UpdateCountriesContext.IS_OBSOLETE, false);
    }

    private Map<String, Long> fetchAllCountriesFromApi()
    {
        try
        {
            GetCountriesResponse allCountries = rapidApiClient.fetchCountries(new GetCountriesRequest());

            return resolveResult(allCountries);
        }
        catch (RapidApiException e)
        {
            throw new AppException(ResponseCode.HTTP_CLIENT_EXCEPTION, e.getMessage());
        }
    }

    private Map<String, Long> resolveResult(final GetCountriesResponse result)
    {
        Map<String, Long> countries = new HashMap<>();

        for (int i = 0; i < result.getResponse().size(); i++)
        {
            countries.put(result.getResponse().get(i), (long)i);
        }

        return countries;
    }
}
