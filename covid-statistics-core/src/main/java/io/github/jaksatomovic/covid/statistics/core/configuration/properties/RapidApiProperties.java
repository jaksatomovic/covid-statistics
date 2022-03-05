package io.github.jaksatomovic.covid.statistics.core.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The type Rapid api properties.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@ConfigurationProperties (prefix = "rapid-api")
public class RapidApiProperties
{
    private String host;
    private String key;

    public String getHost()
    {
        return host;
    }

    public void setHost(final String host)
    {
        this.host = host;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(final String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("RapidApiProperties{");
        sb.append("host='").append(host).append('\'');
        sb.append(", key='").append(key).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
