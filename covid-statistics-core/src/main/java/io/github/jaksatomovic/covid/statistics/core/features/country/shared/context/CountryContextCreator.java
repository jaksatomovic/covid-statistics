package io.github.jaksatomovic.covid.statistics.core.features.country.shared.context;

import io.github.jaksatomovic.covid.statistics.commons.api.messages.request.AbstractRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.ContextCreator;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.OperationContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.CountryStore;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CountryContextCreator<C extends OperationContext<? extends AbstractRequest>, R extends AbstractRequest>
    implements ContextCreator<C, R>
{
    protected final CountryStore countryStore;

    @Autowired
    public CountryContextCreator(final CountryStore countryStore)
    {
        this.countryStore = countryStore;
    }

}
