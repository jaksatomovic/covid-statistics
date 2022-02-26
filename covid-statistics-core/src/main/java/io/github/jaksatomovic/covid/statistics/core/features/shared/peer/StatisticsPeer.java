package io.github.jaksatomovic.covid.statistics.core.features.shared.peer;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.StatisticsService;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.delete.DeleteStatisticsOperation;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.search.SearchStatisticsOperation;
import org.springframework.stereotype.Service;

/**
 * The type Statistics peer.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class StatisticsPeer
    implements StatisticsService
{
    private final SearchStatisticsOperation searchStatisticsOperation;
    private final DeleteStatisticsOperation deleteStatisticsOperation;

    public StatisticsPeer(final SearchStatisticsOperation searchStatisticsOperation, final DeleteStatisticsOperation deleteStatisticsOperation)
    {
        this.searchStatisticsOperation = Defense.notNull(searchStatisticsOperation, SearchStatisticsOperation.class.getSimpleName());
        this.deleteStatisticsOperation = Defense.notNull(deleteStatisticsOperation, DeleteStatisticsOperation.class.getSimpleName());
    }

    @Override
    public SearchStatisticsResponse searchBy(final SearchStatisticsRequest request)
        throws ApiException
    {
        return searchStatisticsOperation.execute(request);
    }

    @Override
    public DeleteStatisticsResponse deleteBy(final DeleteStatisticsRequest request)
        throws ApiException
    {
        return deleteStatisticsOperation.execute(request);
    }
}
