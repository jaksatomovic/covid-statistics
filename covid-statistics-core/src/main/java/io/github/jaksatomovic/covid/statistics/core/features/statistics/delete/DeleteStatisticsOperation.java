package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Update countries operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class DeleteStatisticsOperation
    extends MainOperation<DeleteStatisticsRequest, DeleteStatisticsResponse, DeleteStatisticsContext>
{
    private final DeleteStatisticsRequestValidator       deleteStatisticsRequestValidator;
    private final DeleteStatisticsPreconditionsValidator deleteStatisticsPreconditionsValidator;
    private final DeleteStatisticsContextCreator         deleteStatisticsContextCreator;
    private final DeleteStatisticsResourceMaintainer     deleteStatisticsResourceMaintainer;
    private final DeleteStatisticsResponseGenerator      deleteStatisticsResponseGenerator;

    public DeleteStatisticsOperation(final DeleteStatisticsRequestValidator deleteStatisticsRequestValidator, final DeleteStatisticsPreconditionsValidator deleteStatisticsPreconditionsValidator, final DeleteStatisticsContextCreator deleteStatisticsContextCreator, final DeleteStatisticsResourceMaintainer deleteStatisticsResourceMaintainer, final DeleteStatisticsResponseGenerator deleteStatisticsResponseGenerator)
    {
        this.deleteStatisticsRequestValidator = Defense.notNull(deleteStatisticsRequestValidator, DeleteStatisticsRequestValidator.class.getSimpleName());
        this.deleteStatisticsPreconditionsValidator = Defense.notNull(deleteStatisticsPreconditionsValidator, DeleteStatisticsPreconditionsValidator.class.getSimpleName());
        this.deleteStatisticsContextCreator = Defense.notNull(deleteStatisticsContextCreator, DeleteStatisticsContextCreator.class.getSimpleName());
        this.deleteStatisticsResourceMaintainer = Defense.notNull(deleteStatisticsResourceMaintainer, DeleteStatisticsResourceMaintainer.class.getSimpleName());
        this.deleteStatisticsResponseGenerator = Defense.notNull(deleteStatisticsResponseGenerator, DeleteStatisticsResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final DeleteStatisticsRequest request)
    {
        deleteStatisticsRequestValidator.validate(request);
    }

    @Override
    protected DeleteStatisticsContext createContext(final DeleteStatisticsRequest request)
    {
        return deleteStatisticsContextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final DeleteStatisticsContext context)
    {
        deleteStatisticsPreconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final DeleteStatisticsContext context)
    {
        deleteStatisticsResourceMaintainer.resolve(context);
    }

    @Override
    protected DeleteStatisticsResponse generateResponse(final DeleteStatisticsContext context)
    {
        return deleteStatisticsResponseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.DELETE_STATISTICS;
    }
}
