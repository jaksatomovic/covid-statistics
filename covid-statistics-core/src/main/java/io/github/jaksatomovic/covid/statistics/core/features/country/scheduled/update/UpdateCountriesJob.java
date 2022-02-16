package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * The type Update countries job.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class UpdateCountriesJob
{
    private final UpdateCountriesOperation updateCountriesOperation;

    /**
     * Instantiates a new Update countries job.
     *
     * @param updateCountriesOperation the update country operation
     */
    public UpdateCountriesJob(final UpdateCountriesOperation updateCountriesOperation)
    {
        this.updateCountriesOperation = Defense.notNull(updateCountriesOperation, UpdateCountriesContext.class.getSimpleName());
    }

    /**
     * Process.
     */
//    @Scheduled (cron = "0 * * * *")
    @Scheduled (cron = "0 */1 * ? * *")
    public void process()
    {
        updateCountriesOperation.execute(new UpdateCountriesRequest());
    }
}
