package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.configuration.properties.RapidApiProperties;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.Executor;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetCountriesRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetStatisticsRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetCountriesResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetStatisticsResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
// TODO [JT] move whole client to separate module
@Service
public class RapidApiClient
    extends BaseClient
{

    private static final String RAPIDAPI_HOST_HEADER_KEYWORD = "x-rapidapi-host";
    private static final String RAPIDAPI_KEY_HEADER_KEYWORD  = "x-rapidapi-key";
    private static final String RAPID_API_BASE_URL           = "https://covid-193.p.rapidapi.com";
    private static final String HISTORY_URL                  = RAPID_API_BASE_URL + "/history";
    private static final String COUNTRIES_URL                = RAPID_API_BASE_URL + "/countries";
    private static final String STATISTICS_URL               = RAPID_API_BASE_URL + "/statistics";

    private final RapidApiProperties properties;

    public RapidApiClient(final RapidApiProperties properties)
    {
        this.properties = Defense.notNull(properties, RapidApiProperties.class.getSimpleName());
    }

    public GetCountriesResponse fetchCountries(final GetCountriesRequest request)
        throws RapidApiException
    {
        return secureExecute(new Executor<GetCountriesRequest, GetCountriesResponse>()
        {
            @Override
            public GetCountriesRequest getRequest()
            {
                return request;
            }

            @Override
            public GetCountriesResponse execute(final GetCountriesRequest request)
            {
                logger.info("External Countries fetch");

                String url = COUNTRIES_URL;

                ResponseEntity<GetCountriesResponse> result = getRestTemplate().exchange(
                    url,
                    HttpMethod.GET,
                    createHttpRequest(null, fillHeader()),
                    GetCountriesResponse.class
                );

                logger.info("External Countries fetched - response: {}", result.getBody());
                return result.getBody();
            }
        });
    }

    public GetStatisticsResponse fetchStatistics(final GetStatisticsRequest request)
        throws RapidApiException
    {
        return secureExecute(new Executor<GetStatisticsRequest, GetStatisticsResponse>()
        {
            @Override
            public GetStatisticsRequest getRequest()
            {
                return request;
            }

            @Override
            public GetStatisticsResponse execute(final GetStatisticsRequest request)
            {
                logger.info("GET Statistics - request: {}", request);

                String url = STATISTICS_URL;

                validateGetStatisticsRequest(request);

                ResponseEntity<GetStatisticsResponse> result = getRestTemplate().exchange(
                    url,
                    HttpMethod.GET,
                    createHttpRequest(request, fillHeader()),
                    GetStatisticsResponse.class
                );

                logger.info("GET Statistics - response: {}", result.getBody());
                return result.getBody();
            }

            private void validateGetStatisticsRequest(GetStatisticsRequest request)
            {
                Defense.notNull(request, "GetStatisticsRequest");
            }
        });
    }

    public GetHistoryResponse fetchHistory(final GetHistoryRequest request)
        throws RapidApiException
    {
        return secureExecute(new Executor<GetHistoryRequest, GetHistoryResponse>()
        {
            @Override
            public GetHistoryRequest getRequest()
            {
                return request;
            }

            @Override
            public GetHistoryResponse execute(final GetHistoryRequest request)
            {
                logger.info("GET History - request: {}", request);

                String url = HISTORY_URL + "?country=" + request.getCountry().toLowerCase() + "&day=" + request.getDate().toString();

                validateGetHistoryRequest(request);

                ResponseEntity<GetHistoryResponse> result = getRestTemplate().exchange(
                    url,
                    HttpMethod.GET,
                    createHttpRequest(request, fillHeader()),
                    GetHistoryResponse.class
                );

                logger.info("GET History - response: {}", result.getBody());
                return result.getBody();
            }

            private void validateGetHistoryRequest(GetHistoryRequest request)
            {
                Defense.notNull(request, "GetCountriesRequest");
                Defense.notNull(request.getCountry(), "country");
                Defense.notNull(request.getDate(), "date");
            }
        });
    }

    @Override
    HttpHeaders fillHeader()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add(RAPIDAPI_HOST_HEADER_KEYWORD, properties.getHost());
        headers.add(RAPIDAPI_KEY_HEADER_KEYWORD, properties.getKey());
        return headers;
    }
}
