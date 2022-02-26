package io.github.jaksatomovic.covid.statistics.web.controller;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.delete.DeleteStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.api.features.statistics.search.SearchStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.api.shared.Statistics;
import io.github.jaksatomovic.covid.statistics.commons.exception.ApiException;
import io.github.jaksatomovic.covid.statistics.core.features.shared.peer.StatisticsPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * The type Statistics controller.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@RestController
@RequestMapping ("/statistics")
@Api (value = "COVID-19 Statistics Service")
public class StatisticsController
{
    private final StatisticsPeer peer;

    /**
     * Instantiates a new Statistics controller.
     *
     * @param peer the peer
     */
    public StatisticsController(final StatisticsPeer peer)
    {
        this.peer = Defense.notNull(peer, StatisticsPeer.class.getSimpleName());
    }

    /**
     * Search statistics.
     *
     * @param country  the country
     * @param dateFrom the date from
     * @param dateTo   the date to
     * @return the statistics
     * @throws ApiException the api exception
     */
    @ApiOperation (value = "Searches the Statistics based on the provided JSON.")
    @GetMapping
    public @ResponseBody
    Statistics search(
        @RequestParam (value = "country") String country,
        @RequestParam (value = "dateFrom") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
        @RequestParam (value = "dateTo") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dateTo
    )
        throws ApiException
    {
        SearchStatisticsRequest request = new SearchStatisticsRequest();

        request.setCountry(country);
        request.setDateFrom(dateFrom);
        request.setDateTo(dateTo);

        return peer.searchBy(request).getPayload();
    }

    /**
     * Delete delete statistics response.
     *
     * @param country  the country
     * @param dateFrom the date from
     * @param dateTo   the date to
     * @return the delete statistics response
     * @throws ApiException the api exception
     */
    @ApiOperation (value = "Deletes the searched statistics by the specified date range and country.")
    @DeleteMapping
    public @ResponseBody
    DeleteStatisticsResponse delete(
        @RequestParam (value = "country") String country,
        @RequestParam (value = "dateFrom") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
        @RequestParam (value = "dateTo") @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dateTo
    )
        throws ApiException
    {
        final DeleteStatisticsRequest request = new DeleteStatisticsRequest();
        request.setCountry(country);
        request.setDateFrom(dateFrom);
        request.setDateTo(dateTo);

        return peer.deleteBy(request);
    }
}
