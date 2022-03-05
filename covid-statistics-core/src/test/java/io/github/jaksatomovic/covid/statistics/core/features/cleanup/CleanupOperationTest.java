package io.github.jaksatomovic.covid.statistics.core.features.cleanup;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupResponse;
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
        CleanupOperation.class,
    }
)
class CleanupOperationTest
    extends AbstractTest
{

    @Autowired
    private CleanupOperation operation;

    @MockBean
    private CleanupRequestValidator       validator;
    @MockBean
    private CleanupPreconditionsValidator preconditionsValidator;
    @MockBean
    private CleanupContextCreator         contextCreator;
    @MockBean
    private CleanupResourceMaintainer     resourceMaintainer;
    @MockBean
    private CleanupResponseGenerator      responseGenerator;

    @Test
    void execute_OK()
        throws ApiException
    {
        CleanupRequest request = new CleanupRequest();
        CleanupContext context = getCleanupContext();
        Mockito.when(contextCreator.create(request)).thenReturn(context);
        Mockito.when(responseGenerator.generateResponse(context)).thenReturn(prepareResponse(request));

        CleanupResponse response = operation.execute(request);

        Assertions.assertNotNull(response);

        Mockito.verify(validator).validate(request);
        Mockito.verify(contextCreator).create(request);
        Mockito.verify(preconditionsValidator).validate(context);
        Mockito.verify(resourceMaintainer).resolve(context);
        Mockito.verify(responseGenerator).generateResponse(context);
    }

    private CleanupResponse prepareResponse(final CleanupRequest request)
    {
        return new CleanupResponse(request, ResponseCode.OK);
    }
}