package io.github.jaksatomovic.covid.statistics.core.features.shared.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Validator used to determine whether entity exists.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public abstract class EntityFoundValidator<T>
    implements Validator<Optional<T>>
{
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void validate(final Optional<T> input)
    {
        if (input.isPresent())
        {
            logger.trace("[VALIDATION] Entity Exists - [OK]");
            return;
        }
        logger.trace("[VALIDATION] Entity Exists - [ERROR]");
        throw new EntityNotFoundException(getMessage());
    }

    protected abstract String getMessage();

}