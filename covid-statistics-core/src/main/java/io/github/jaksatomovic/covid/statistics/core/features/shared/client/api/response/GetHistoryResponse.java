package io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.jaksatomovic.covid.statistics.core.features.shared.client.api.RapidApiResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Get history request.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
public class GetHistoryResponse
    extends RapidApiResponse
{
    private String         get;
    private Parameters     parameters;
    private int            results;
    private List<Object>   errors   = new ArrayList<>();
    private List<Response> response = new ArrayList<>();

    public String getGet()
    {
        return get;
    }

    public void setGet(final String get)
    {
        this.get = get;
    }

    public Parameters getParameters()
    {
        return parameters;
    }

    public void setParameters(final Parameters parameters)
    {
        this.parameters = parameters;
    }

    public int getResults()
    {
        return results;
    }

    public void setResults(final int results)
    {
        this.results = results;
    }

    public List<Object> getErrors()
    {
        return errors;
    }

    public void setErrors(final List<Object> errors)
    {
        this.errors = errors;
    }

    public List<Response> getResponse()
    {
        return response;
    }

    public void setResponse(final List<Response> response)
    {
        this.response = response;
    }

    public static class Parameters
    {
        private String country;
        private String day;

        public String getCountry()
        {
            return country;
        }

        public void setCountry(final String country)
        {
            this.country = country;
        }

        public String getDay()
        {
            return day;
        }

        public void setDay(final String day)
        {
            this.day = day;
        }
    }

    public static class Cases
    {
        @JsonProperty ("new")
        private String newCases;
        private int    active;
        private int    critical;
        private int    recovered;
        @JsonProperty ("1M_pop")
        private String OneMPopulation;
        private int    total;

        public String getNewCases()
        {
            return newCases;
        }

        public void setNewCases(final String newCases)
        {
            this.newCases = newCases;
        }

        public int getActive()
        {
            return active;
        }

        public void setActive(final int active)
        {
            this.active = active;
        }

        public int getCritical()
        {
            return critical;
        }

        public void setCritical(final int critical)
        {
            this.critical = critical;
        }

        public int getRecovered()
        {
            return recovered;
        }

        public void setRecovered(final int recovered)
        {
            this.recovered = recovered;
        }

        public String getOneMPopulation()
        {
            return OneMPopulation;
        }

        public void setOneMPopulation(final String oneMPopulation)
        {
            OneMPopulation = oneMPopulation;
        }

        public int getTotal()
        {
            return total;
        }

        public void setTotal(final int total)
        {
            this.total = total;
        }
    }

    public static class Deaths
    {
        @JsonProperty ("new")
        private String newDeaths;
        @JsonProperty ("1M_pop")
        private String OneMPopulation;
        private int    total;

        public String getNewDeaths()
        {
            return newDeaths;
        }

        public void setNewDeaths(final String newDeaths)
        {
            this.newDeaths = newDeaths;
        }

        public String getOneMPopulation()
        {
            return OneMPopulation;
        }

        public void setOneMPopulation(final String oneMPopulation)
        {
            OneMPopulation = oneMPopulation;
        }

        public int getTotal()
        {
            return total;
        }

        public void setTotal(final int total)
        {
            this.total = total;
        }
    }

    public static class Tests
    {
        @JsonProperty ("1M_pop")
        private String OneMPopulation;
        private int    total;

        public String getOneMPopulation()
        {
            return OneMPopulation;
        }

        public void setOneMPopulation(final String oneMPopulation)
        {
            OneMPopulation = oneMPopulation;
        }

        public int getTotal()
        {
            return total;
        }

        public void setTotal(final int total)
        {
            this.total = total;
        }
    }

    public static class Response
    {
        private String continent;
        private String country;
        private int    population;
        private Cases  cases;
        private Deaths deaths;
        private Tests  tests;
        private String day;
        @JsonProperty ("time")
        private Date   date;

        public String getContinent()
        {
            return continent;
        }

        public void setContinent(final String continent)
        {
            this.continent = continent;
        }

        public String getCountry()
        {
            return country;
        }

        public void setCountry(final String country)
        {
            this.country = country;
        }

        public int getPopulation()
        {
            return population;
        }

        public void setPopulation(final int population)
        {
            this.population = population;
        }

        public Cases getCases()
        {
            return cases;
        }

        public void setCases(final Cases cases)
        {
            this.cases = cases;
        }

        public Deaths getDeaths()
        {
            return deaths;
        }

        public void setDeaths(final Deaths deaths)
        {
            this.deaths = deaths;
        }

        public Tests getTests()
        {
            return tests;
        }

        public void setTests(final Tests tests)
        {
            this.tests = tests;
        }

        public String getDay()
        {
            return day;
        }

        public void setDay(final String day)
        {
            this.day = day;
        }

        public Date getDate()
        {
            return date;
        }

        public void setDate(final Date date)
        {
            this.date = date;
        }
    }
}






