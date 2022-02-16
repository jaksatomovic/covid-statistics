package io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response;

import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Get all countries.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class GetCountriesResponse
    extends RapidApiResponse
{
    private String       get;
    private Integer      results;
    private List<Object> parameters = new ArrayList<>();
    private List<Object> errors     = new ArrayList<>();
    private List<String> response   = new ArrayList<>();

    /**
     * Gets get.
     *
     * @return the get
     */
    public String getGet()
    {
        return get;
    }

    /**
     * Sets get.
     *
     * @param get the get
     */
    public void setGet(String get)
    {
        this.get = get;
    }

    /**
     * Gets parameters.
     *
     * @return the parameters
     */
    public List<Object> getParameters()
    {
        return parameters;
    }

    /**
     * Sets parameters.
     *
     * @param parameters the parameters
     */
    public void setParameters(List<Object> parameters)
    {
        this.parameters = parameters;
    }

    /**
     * Gets errors.
     *
     * @return the errors
     */
    public List<Object> getErrors()
    {
        return errors;
    }

    /**
     * Sets errors.
     *
     * @param errors the errors
     */
    public void setErrors(List<Object> errors)
    {
        this.errors = errors;
    }

    /**
     * Gets results.
     *
     * @return the results
     */
    public Integer getResults()
    {
        return results;
    }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(Integer results)
    {
        this.results = results;
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public List<String> getResponse()
    {
        return response;
    }

    /**
     * Sets response.
     *
     * @param response the response
     */
    public void setResponse(List<String> response)
    {
        this.response = response;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("GetAllCountries{");
        sb.append("get='").append(get).append('\'');
        sb.append(", results=").append(results);
        sb.append(", parameters=").append(parameters);
        sb.append(", errors=").append(errors);
        sb.append(", response=").append(response);
        sb.append('}');
        return sb.toString();
    }
}
