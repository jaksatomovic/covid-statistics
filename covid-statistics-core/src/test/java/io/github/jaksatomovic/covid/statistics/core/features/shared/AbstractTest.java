package io.github.jaksatomovic.covid.statistics.core.features.shared;

import io.github.jaksatomovic.commons.api.ResponseCode;
import io.github.jaksatomovic.covid.statistics.api.features.cleanup.CleanupRequest;
import io.github.jaksatomovic.covid.statistics.api.features.country.update.UpdateCountriesRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;
import io.github.jaksatomovic.covid.statistics.core.features.cleanup.CleanupContext;
import io.github.jaksatomovic.covid.statistics.core.features.country.scheduled.update.UpdateCountriesContext;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.context.Mutable;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.delete.DeleteStatisticsContext;
import io.github.jaksatomovic.covid.statistics.core.features.statistics.search.SearchStatisticsContext;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbCountry;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbSearch;
import io.github.jaksatomovic.covid.statistics.core.persistence.domain.DbStatistics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class AbstractTest
{

    private static final String CROATIA          = "Croatia";
    private static final int    DAYS_TO_SUBTRACT = 5;
    private static final double PERCENTAGE_VALUE = 1.0;

    protected CleanupContext getCleanupContext()
    {
        return new CleanupContext()
        {
            @Override
            public Optional<List<DbSearch>> getAllSearches()
            {
                return Optional.of(Collections.singletonList(prepareDbSearch()));
            }

            @Override
            public CleanupRequest getOriginalRequest()
            {
                return new CleanupRequest();
            }
        };
    }

    protected UpdateCountriesContext getUpdateCountriesContext(final UpdateCountriesRequest request)
    {

        return new UpdateCountriesContext()
        {
            @Override
            public Optional<Map<String, Long>> getExternalCountries()
            {
                return Optional.of(Collections.singletonMap(CROATIA, 1L));
            }

            @Override
            public Mutable<Boolean> isObsolete()
            {
                return new Mutable<>(UpdateCountriesContext.IS_OBSOLETE, true);
            }

            @Override
            public Optional<Map<String, Long>> getExistingCountries()
            {
                return Optional.of(Collections.singletonMap(CROATIA, 1L));
            }

            @Override
            public UpdateCountriesRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    protected DeleteStatisticsRequest prepareDeleteStatisticsRequest()
    {
        DeleteStatisticsRequest request = new DeleteStatisticsRequest();

        request.setCountry(CROATIA);
        request.setDateTo(LocalDate.now());
        request.setDateFrom(LocalDate.now().minusDays(DAYS_TO_SUBTRACT));

        return request;
    }

    protected DeleteStatisticsContext getDeleteStatisticsContext(final DeleteStatisticsRequest request)
    {
        return new DeleteStatisticsContext()
        {
            @Override
            public Optional<List<DbSearch>> getExistingSearches()
            {
                return Optional.of(Collections.singletonList(prepareDbSearch()));
            }

            @Override
            public Optional<DbCountry> getCountry()
            {
                return Optional.of(new DbCountry());
            }

            @Override
            public DeleteStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    protected SearchStatisticsRequest prepareSearchStatisticsRequest()
    {
        SearchStatisticsRequest request = new SearchStatisticsRequest();
        request.setCountry(CROATIA);
        request.setDateTo(LocalDate.now());
        request.setDateFrom(LocalDate.now().minusDays(DAYS_TO_SUBTRACT));
        return request;
    }

    protected SearchStatisticsResponse prepareSearchStatisticsResponse(final SearchStatisticsRequest request)
    {
        return new SearchStatisticsResponse(request, ResponseCode.OK, new Statistics(PERCENTAGE_VALUE, PERCENTAGE_VALUE, PERCENTAGE_VALUE, PERCENTAGE_VALUE));
    }

    protected SearchStatisticsContext getSearchStatisticsContext(final SearchStatisticsRequest request)
    {

        return new SearchStatisticsContext()
        {
            @Override
            public Mutable<GetHistoryResponse> getCasesFrom()
            {
                return new Mutable<>(GetHistoryResponse.class.getSimpleName(), new GetHistoryResponse());
            }

            @Override
            public Mutable<GetHistoryResponse> getCasesTo()
            {
                return new Mutable<>(GetHistoryResponse.class.getSimpleName(), new GetHistoryResponse());
            }

            @Override
            public Mutable<DbStatistics> getDbStatistics()
            {
                return new Mutable<>(DbStatistics.class.getSimpleName(), prepareDbStatistics());
            }

            @Override
            public Optional<DbCountry> getCountry()
            {
                return Optional.of(new DbCountry(1L, CROATIA));
            }

            @Override
            public Optional<List<DbSearch>> getExistingSearches()
            {
                return Optional.of(Collections.singletonList(prepareDbSearch()));
            }

            @Override
            public SearchStatisticsRequest getOriginalRequest()
            {
                return request;
            }
        };
    }

    private DbStatistics prepareDbStatistics()
    {
        DbStatistics dbStatistics = new DbStatistics();
        dbStatistics.setActiveCases(PERCENTAGE_VALUE);
        dbStatistics.setCriticalCases(PERCENTAGE_VALUE);
        dbStatistics.setSearch(prepareDbSearch());
        dbStatistics.setNewCases(PERCENTAGE_VALUE);
        dbStatistics.setRecoveredCases(PERCENTAGE_VALUE);
        return dbStatistics;
    }

    private DbSearch prepareDbSearch()
    {
        DbSearch dbSearch = new DbSearch();
        dbSearch.setCountry(new DbCountry(1L, CROATIA));
        dbSearch.setId(1L);
        dbSearch.setDateFrom(LocalDate.now().minusDays(DAYS_TO_SUBTRACT));
        dbSearch.setDateTo(LocalDate.now());
        return dbSearch;
    }
}
