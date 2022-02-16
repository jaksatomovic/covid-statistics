package io.github.jaksatomovic.covid.statistics.core.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.sql.Driver;

/**
 * Represents Database configuration properties
 *
 * @author Jaksa Tomovic
 * @since 1.0
 */
@ConfigurationProperties (prefix = "database")
public class DatabaseProperties
{
    private String        host;
    private Integer       port;
    private String        name;
    private String        username;
    private String        password;
    private Integer       initialSize;
    private Integer       minimumIdle;
    private Integer       maximumPoolSize;
    private Class<Driver> driverClassName;

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getInitialSize()
    {
        return initialSize;
    }

    public void setInitialSize(Integer initialSize)
    {
        this.initialSize = initialSize;
    }

    public Integer getMinimumIdle()
    {
        return minimumIdle;
    }

    public void setMinimumIdle(Integer minimumIdle)
    {
        this.minimumIdle = minimumIdle;
    }

    public Integer getMaximumPoolSize()
    {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize)
    {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Class<Driver> getDriverClassName()
    {
        return driverClassName;
    }

    public void setDriverClassName(Class<Driver> driverClassName)
    {
        this.driverClassName = driverClassName;
    }

    public String getDatabaseJdbcUrl()
    {
        return String.format("jdbc:postgresql://%s:%s/%s", getHost(), getPort(), getName());
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ReportingDatabaseProperties{");
        sb.append("host='").append(host).append('\'');
        sb.append(", port=").append(port);
        sb.append(", name='").append(name).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", initialSize=").append(initialSize);
        sb.append(", minIdle=").append(minimumIdle);
        sb.append(", maxSize=").append(maximumPoolSize);
        sb.append(", driverClassName=").append(driverClassName);
        sb.append('}');
        return sb.toString();
    }
}