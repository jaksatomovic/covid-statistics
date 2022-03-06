package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Update countries operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class CleanupOperation
    extends MainOperation<CleanupRequest, CleanupResponse, CleanupContext>
{
    private final CleanupRequestValidator       validator;
    private final CleanupPreconditionsValidator preconditionsValidator;
    private final CleanupContextCreator         contextCreator;
    private final CleanupResourceMaintainer     resourceMaintainer;
    private final CleanupResponseGenerator      responseGenerator;

    public CleanupOperation(final CleanupRequestValidator validator, final CleanupPreconditionsValidator preconditionsValidator, final CleanupContextCreator contextCreator, final CleanupResourceMaintainer resourceMaintainer, final CleanupResponseGenerator responseGenerator)
    {
        this.validator = Defense.notNull(validator, CleanupRequestValidator.class.getSimpleName());
        this.preconditionsValidator = Defense.notNull(preconditionsValidator, CleanupPreconditionsValidator.class.getSimpleName());
        this.contextCreator = Defense.notNull(contextCreator, CleanupContextCreator.class.getSimpleName());
        this.resourceMaintainer = Defense.notNull(resourceMaintainer, CleanupResourceMaintainer.class.getSimpleName());
        this.responseGenerator = Defense.notNull(responseGenerator, CleanupResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final CleanupRequest request)
    {
        validator.validate(request);
    }

    @Override
    protected CleanupContext createContext(final CleanupRequest request)
    {
        return contextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final CleanupContext context)
    {
        preconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final CleanupContext context)
    {
        resourceMaintainer.resolve(context);
    }

    @Override
    protected CleanupResponse generateResponse(final CleanupContext context)
    {
        return responseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.CLEANUP;
    }
}
