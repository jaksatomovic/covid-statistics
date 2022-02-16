package io.github.jaksatomovic.covid.statistics.core.features.shared.validator.context;

import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.features.shared.validator.Validator;

/**
 * Preconditions Validator interface.
 * As a precondition for successful provisioning of a resource, all rules defined for specific operation need to be verified.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public interface PreconditionsValidator<C extends OperationContext>
    extends Validator<C>
{
}
