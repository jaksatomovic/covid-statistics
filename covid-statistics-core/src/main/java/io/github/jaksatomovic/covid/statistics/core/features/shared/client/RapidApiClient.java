package io.github.jaksatomovic.covid.statistics.core.features.shared.client;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.Executor;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetCountriesRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.request.GetHistoryRequest;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetCountriesResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response.GetHistoryResponse;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.exception.RapidApiException;
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
                logger.info("External Countries fetch - request: {}", request);

                String url = "https://covid-193.p.rapidapi.com/countries";

                validateGetCountriesRequest(request);

                ResponseEntity<GetCountriesResponse> result = getRestTemplate().exchange(
                    url,
                    HttpMethod.GET,
                    createHttpRequest(request, fillHeader()),
                    GetCountriesResponse.class
                );

                logger.info("External Countries fetched - response: {}", result.getBody());
                return result.getBody();
            }

            private void validateGetCountriesRequest(GetCountriesRequest request)
            {
                Defense.notNull(request, "GetCountriesRequest");
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

                String url = "https://covid-193.p.rapidapi.com/history?country=" + request.getCountry().toLowerCase() + "&day=" + request.getDate().toString();

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
        // TODO application properties
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-rapidapi-host", "covid-193.p.rapidapi.com");
        headers.add("x-rapidapi-key", "cfa175a786mshb2b747bdc65bf35p1723f0jsn20ebc1cdec4e");
        return headers;
    }
}
