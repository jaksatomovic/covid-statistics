package io.github.jaksatomovic.covid.statistics.core.features.country.shared.context;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;

import java.util.Map;
import java.util.Optional;

public interface CountryContext<R extends AbstractRequest>
    extends OperationContext<R>
{
    Optional<Map<String, Long>> getExistingCountries();
}
