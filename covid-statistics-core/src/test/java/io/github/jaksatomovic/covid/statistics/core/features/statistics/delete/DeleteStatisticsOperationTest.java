package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.AbstractTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@SpringBootTest
@TestInstance (TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration (
    classes = {
        DeleteStatisticsOperation.class,
    }
)
class DeleteStatisticsOperationTest
    extends AbstractTest
{

    @Autowired
    private DeleteStatisticsOperation operation;

    @MockBean
    private DeleteStatisticsRequestValidator       validator;
    @MockBean
    private DeleteStatisticsPreconditionsValidator preconditionsValidator;
    @MockBean
    private DeleteStatisticsContextCreator         contextCreator;
    @MockBean
    private DeleteStatisticsResourceMaintainer     resourceMaintainer;
    @MockBean
    private DeleteStatisticsResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        DeleteStatisticsRequest request = prepareDeleteStatisticsRequest();
        DeleteStatisticsContext context = getDeleteStatisticsContext(request);
        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        DeleteStatisticsResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private DeleteStatisticsResponse prepareResponse(final DeleteStatisticsRequest request)
    {
        return new DeleteStatisticsResponse(request, ResponseCode.OK);
    }
}
