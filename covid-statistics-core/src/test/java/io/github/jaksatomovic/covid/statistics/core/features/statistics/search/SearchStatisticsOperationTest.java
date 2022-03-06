package io.github.jaksatomovic.covid.statistics.core.features.statistics.search;

import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
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
        SearchStatisticsOperation.class,
    }
)
class SearchStatisticsOperationTest
    extends AbstractTest
{

    @Autowired
    private SearchStatisticsOperation operation;

    @MockBean
    private SearchStatisticsRequestValidator       validator;
    @MockBean
    private SearchStatisticsPreconditionsValidator preconditionsValidator;
    @MockBean
    private SearchStatisticsContextCreator         contextCreator;
    @MockBean
    private SearchStatisticsResourceMaintainer     resourceMaintainer;
    @MockBean
    private SearchStatisticsResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        SearchStatisticsRequest request = prepareSearchStatisticsRequest();
        SearchStatisticsContext context = getSearchStatisticsContext(request);
        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareSearchStatisticsResponse(request));

        SearchStatisticsResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }
}