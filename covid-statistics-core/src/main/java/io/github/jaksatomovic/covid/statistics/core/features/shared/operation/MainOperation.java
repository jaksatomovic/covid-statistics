package io.github.jaksatomovic.covid.statistics.core.features.shared.operation;

import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.commons.api.messages.response.AbstractResponse;
import io.github.jaksatomovic.covid.statistics.commons.utility.ResponseCode;
import io.github.jaksatomovic.covid.statistics.core.exception.AppException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import org.omg.CORBA.portable.UnknownException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for all provisioning operations.
 * Each operation goes through series of steps:
 * 1. Request validation - check if all required fields are set
 * 2. Context creation - creation of context that contain all the needed data for next phases of the flow
 * 3. Preconditions validation - each operation has a certain preconditions that need to be valid
 * 4. Resource provisioning - changing the state of the resource
 * 5. Response generation - generation of the response based on provided context
 *
 * @param <I> Input (request) type
 * @param <O> Output (response) type
 * @param <C> Context type
 */
public abstract class MainOperation<I extends AbstractRequest, O extends AbstractResponse, C extends OperationContext>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional (noRollbackFor = AppException.class)
    public O execute(I request)
        throws AppException
    {
        try
        {
            logger.info("-> Request: {}", request);
            O response = executeFlow(request);
            logger.info("<- Response: {}", response);
            return response;
        }
        catch (AppException pse)
        {
            logger.error("Service exception: {}", pse.getMessage());
            throw new AppException(ResponseCode.UNKNOWN, pse.getMessage());
        }
        catch (Exception e)
        {
            logger.error("System exception: ", e);
            throw new UnknownException(e.getCause());
        }
    }

    O executeFlow(I request)
    {
        validateRequest(request);
        logger.debug("Input Validation Result - [OK]");
        C context = createContext(request);
        logger.debug("Context created - [OK]");
        validatePreconditions(context);
        logger.debug("Preconditions Validation Result - [OK]");
        resolveResources(context);
        logger.debug("Provisioning Result - [OK]");
        return generateResponse(context);
    }

    protected abstract void validateRequest(final I request);

    protected abstract C createContext(final I request);

    protected abstract void validatePreconditions(final C context);

    protected abstract void resolveResources(final C context);

    protected abstract O generateResponse(final C context);

    protected abstract Operation getOperation();

    public enum Operation
    {
        UPDATE_COUNTRIES,
    }
}
