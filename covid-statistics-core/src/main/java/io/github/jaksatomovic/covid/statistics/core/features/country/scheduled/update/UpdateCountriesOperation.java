package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Update countries operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class UpdateCountriesOperation
    extends MainOperation<UpdateCountriesRequest, UpdateCountriesResponse, UpdateCountriesContext>
{
    private final UpdateCountriesRequestValidator       updateCountriesRequestValidator;
    private final UpdateCountriesPreconditionsValidator updateCountriesPreconditionsValidator;
    private final UpdateCountriesContextCreator         updateCountriesContextCreator;
    private final UpdateCountriesResourceMaintainer     updateCountriesResourceMaintainer;
    private final UpdateCountriesResponseGenerator      updateCountriesResponseGenerator;

    /**
     * Instantiates a new Update countries operation.
     *
     * @param updateCountriesRequestValidator       the update countries request validator
     * @param updateCountriesPreconditionsValidator the update countries preconditions validator
     * @param updateCountriesContextCreator         the update countries context creator
     * @param updateCountriesResourceMaintainer     the update countries resource maintainer
     * @param updateCountriesResponseGenerator      the update countries response generator
     */
    public UpdateCountriesOperation(final UpdateCountriesRequestValidator updateCountriesRequestValidator, final UpdateCountriesPreconditionsValidator updateCountriesPreconditionsValidator, final UpdateCountriesContextCreator updateCountriesContextCreator, final UpdateCountriesResourceMaintainer updateCountriesResourceMaintainer, final UpdateCountriesResponseGenerator updateCountriesResponseGenerator)
    {
        this.updateCountriesRequestValidator = Defense.notNull(updateCountriesRequestValidator, UpdateCountriesRequestValidator.class.getSimpleName());
        this.updateCountriesPreconditionsValidator = Defense.notNull(updateCountriesPreconditionsValidator, UpdateCountriesRequestValidator.class.getSimpleName());
        this.updateCountriesContextCreator = Defense.notNull(updateCountriesContextCreator, UpdateCountriesContextCreator.class.getSimpleName());
        this.updateCountriesResourceMaintainer = Defense.notNull(updateCountriesResourceMaintainer, UpdateCountriesResourceMaintainer.class.getSimpleName());
        this.updateCountriesResponseGenerator = Defense.notNull(updateCountriesResponseGenerator, UpdateCountriesResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final UpdateCountriesRequest request)
    {
        updateCountriesRequestValidator.validate(request);
    }

    @Override
    protected UpdateCountriesContext createContext(final UpdateCountriesRequest request)
    {
        return updateCountriesContextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final UpdateCountriesContext context)
    {
        updateCountriesPreconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final UpdateCountriesContext context)
    {
        updateCountriesResourceMaintainer.resolve(context);
    }

    @Override
    protected UpdateCountriesResponse generateResponse(final UpdateCountriesContext context)
    {
        return updateCountriesResponseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.UPDATE_COUNTRIES;
    }
}
