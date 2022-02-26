package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.operation.MainOperation;
import org.springframework.stereotype.Service;

/**
 * The type Update countries operation.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class SearchStatisticsOperation
    extends MainOperation<SearchStatisticsRequest, SearchStatisticsResponse, SearchStatisticsContext>
{
    private final SearchStatisticsRequestValidator       searchStatisticsRequestValidator;
    private final SearchStatisticsPreconditionsValidator searchStatisticsPreconditionsValidator;
    private final SearchStatisticsContextCreator         searchStatisticsContextCreator;
    private final SearchStatisticsResourceMaintainer     searchStatisticsResourceMaintainer;
    private final SearchStatisticsResponseGenerator      searchStatisticsResponseGenerator;

    public SearchStatisticsOperation(final SearchStatisticsRequestValidator searchStatisticsRequestValidator, final SearchStatisticsPreconditionsValidator searchStatisticsPreconditionsValidator, final SearchStatisticsContextCreator searchStatisticsContextCreator, final SearchStatisticsResourceMaintainer searchStatisticsResourceMaintainer, final SearchStatisticsResponseGenerator searchStatisticsResponseGenerator)
    {
        this.searchStatisticsRequestValidator = Defense.notNull(searchStatisticsRequestValidator, SearchStatisticsRequestValidator.class.getSimpleName());
        this.searchStatisticsPreconditionsValidator = Defense.notNull(searchStatisticsPreconditionsValidator, SearchStatisticsPreconditionsValidator.class.getSimpleName());
        this.searchStatisticsContextCreator = Defense.notNull(searchStatisticsContextCreator, SearchStatisticsContextCreator.class.getSimpleName());
        this.searchStatisticsResourceMaintainer = Defense.notNull(searchStatisticsResourceMaintainer, SearchStatisticsResourceMaintainer.class.getSimpleName());
        this.searchStatisticsResponseGenerator = Defense.notNull(searchStatisticsResponseGenerator, SearchStatisticsResponseGenerator.class.getSimpleName());
    }

    @Override
    protected void validateRequest(final SearchStatisticsRequest request)
    {
        searchStatisticsRequestValidator.validate(request);
    }

    @Override
    protected SearchStatisticsContext createContext(final SearchStatisticsRequest request)
    {
        return searchStatisticsContextCreator.create(request);
    }

    @Override
    protected void validatePreconditions(final SearchStatisticsContext context)
    {
        searchStatisticsPreconditionsValidator.validate(context);
    }

    @Override
    protected void resolveResources(final SearchStatisticsContext context)
    {
        searchStatisticsResourceMaintainer.resolve(context);
    }

    @Override
    protected SearchStatisticsResponse generateResponse(final SearchStatisticsContext context)
    {
        return searchStatisticsResponseGenerator.generateResponse(context);
    }

    @Override
    protected Operation getOperation()
    {
        return Operation.UPDATE_COUNTRIES;
    }
}
