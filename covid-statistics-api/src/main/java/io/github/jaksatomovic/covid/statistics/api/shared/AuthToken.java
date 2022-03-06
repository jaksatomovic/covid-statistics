package io.github.jaksatomovic.covid.statistics.api.shared;

import io.github.jaksatomovic.commons.api.model.ApiEntity;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class AuthToken
    extends ApiEntity
{
    private String token;
    private String username;

    public AuthToken()
    {

    }

    public AuthToken(String token, String username)
    {
        this.token = token;
        this.username = username;
    }

    public AuthToken(String token)
    {
        this.token = token;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {

        sb.append("token='").append(token).append('\'');
        sb.append(", username='").append(username).append('\'');
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
