package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetCountriesRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetCountriesResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class RapidApiClient
    extends BaseClient
{

    public GetCountriesResponse fetchCountries(final GetCountriesRequest request)
    {

        // TODO secure execute
        logger.info("External Countries fetch - request: {}", request);

        String url = "https://covid-193.p.rapidapi.com/countries";
        logger.trace("External Countries fetch - url: {}", url);

        ResponseEntity<GetCountriesResponse> result = getRestTemplate().exchange(
            url,
            HttpMethod.GET,
            createHttpRequest(request, fillHeader()),
            GetCountriesResponse.class
        );

        logger.info("External Countries fetched - response: {}", result.getBody());

        return result.getBody();
    }

    public GetHistoryResponse fetchHistory(final GetHistoryRequest request)
    {

        // TODO secure execute
        logger.info("External Countries fetch - request: {}", request);
        //validate

        // TODO resolve url from request https://covid-193.p.rapidapi.com/history?country=usa&day=2022-02-22
        String url = "https://covid-193.p.rapidapi.com/history?country=usa&day=2022-02-22";// + request.getCountry() + "&day=" + request.getDate().toString();
        logger.trace("External Countries fetch - url: {}", url);

        ResponseEntity<GetHistoryResponse> result = getRestTemplate().exchange(
            url,
            HttpMethod.GET,
            createHttpRequest(request, fillHeader()),
            GetHistoryResponse.class
        );

        logger.info("External Countries fetched - response: {}", result.getBody());

        return result.getBody();
    }

    @Override
    HttpHeaders fillHeader()
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-host", "covid-193.p.rapidapi.com");
        headers.add("x-rapidapi-key", "cfa175a786mshb2b747bdc65bf35p1723f0jsn20ebc1cdec4e");
        return headers;
    }
}
