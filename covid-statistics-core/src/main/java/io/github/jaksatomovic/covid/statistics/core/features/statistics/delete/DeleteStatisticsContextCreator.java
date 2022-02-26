package io.github.jaksatomovic.covid.statistics.core.features.statistics.delete;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.shared.StatisticsContextCreator;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.CountryStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchResultStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.SearchStore;
import io.github.jaksatomovic.covid.statistics.core.persistence.store.StatisticsStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Update countries context creator.
 */
@Service
public class DeleteStatisticsContextCreator
    extends StatisticsContextCreator<DeleteStatisticsContext, DeleteStatisticsRequest>
{

    private final CountryStore countryStore;

    /**
     * Instantiates a new Statistics context creator.
     *
     * @param statisticsStore   the statistics store
     * @param searchStore       the search store
     * @param searchResultStore the search result store
     * @param countryStore
     */
    protected DeleteStatisticsContextCreator(final StatisticsStore statisticsStore, final SearchStore searchStore, final SearchResultStore searchResultStore, final CountryStore countryStore)
    {
        super(statisticsStore, searchStore, searchResultStore);
        this.countryStore = Defense.notNull(countryStore, CountryStore.class.getSimpleName());
    }

    @Override
    public DeleteStatisticsContext create(final DeleteStatisticsRequest request)
    {
        Optional<DbCountry> dbCountry = resolveCountry(request.getCountry());

        Optional<List<DbSearch>> matchingSearches = Optional.ofNullable(searchStore.getAllBy(dbCountry, request.getDateFrom(), request.getDateTo()));

        return new DeleteStatisticsContext()
        {
            @Override
            public Optional<DbCountry> getCountry()
            {
                return Optional.empty();
            }

            @Override
            public Optional<List<DbSearch>> getExistingSearches()
            {
                return matchingSearches;
            }

            @Override
            public DeleteStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    private Optional<DbCountry> resolveCountry(final String country)
    {
        return Optional.ofNullable(countryStore.getRepository().getByName(country));
    }
}
