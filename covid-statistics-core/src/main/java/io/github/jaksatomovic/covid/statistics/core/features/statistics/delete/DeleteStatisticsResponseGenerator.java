package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.features.shared.generator.ResponseGenerator;
import org.springframework.stereotype.Service;

/**
 * The type Delete statistics response generator.
 */
@Service
public class DeleteStatisticsResponseGenerator
    implements ResponseGenerator<DeleteStatisticsResponse, DeleteStatisticsContext>
{
    @Override
    public DeleteStatisticsResponse generateResponse(final DeleteStatisticsContext context)
    {
        return new DeleteStatisticsResponse(context.getOriginalRequest(), ResponseCode.OK);
    }
}
