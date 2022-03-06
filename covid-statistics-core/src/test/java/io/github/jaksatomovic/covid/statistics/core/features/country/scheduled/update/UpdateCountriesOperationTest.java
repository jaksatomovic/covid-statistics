package io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update;

import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesResponse;
import io.github.jaksatomovic.covid.statistics.commons.api.ResponseCode;
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
        UpdateCountriesOperation.class,
    }
)
class UpdateCountriesOperationTest
    extends AbstractTest
{

    @Autowired
    private UpdateCountriesOperation operation;

    @MockBean
    private UpdateCountriesRequestValidator       validator;
    @MockBean
    private UpdateCountriesPreconditionsValidator preconditionsValidator;
    @MockBean
    private UpdateCountriesContextCreator         contextCreator;
    @MockBean
    private UpdateCountriesResourceMaintainer     resourceMaintainer;
    @MockBean
    private UpdateCountriesResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        UpdateCountriesRequest request = new UpdateCountriesRequest();
        UpdateCountriesContext context = getUpdateCountriesContext(request);
        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        UpdateCountriesResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private UpdateCountriesResponse prepareResponse(final UpdateCountriesRequest request)
    {
        return new UpdateCountriesResponse(request, ResponseCode.OK);
    }
}