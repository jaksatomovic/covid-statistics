package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.maintainer.ResourceMaintainer;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.CountryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * The type Update countries resource maintainer.
 */
@Service
public class UpdateCountriesResourceMaintainer
    implements ResourceMaintainer<UpdateCountriesContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CountryStore countryStore;

    /**
     * Instantiates a new Update countries resource maintainer.
     *
     * @param countryStore the country store
     */
    public UpdateCountriesResourceMaintainer(final CountryStore countryStore)
    {
        this.countryStore = Defense.notNull(countryStore, CountryStore.class.getSimpleName());
    }

    @Override
    public void resolve(final UpdateCountriesContext context)
    {
        if (context.isObsolete().getValue())
        {
            countryStore.deleteAllEntities();
            countryStore.saveAllEntities(context.getExternalCountries().get().keySet().stream().map(DbCountry::new).collect(Collectors.toList()));
            logger.debug("[MAINTAINER] -  Countries updated - [OK]");
            return;
        }

        logger.debug("[MAINTAINER] - No need to update - [OK]");
    }
}
