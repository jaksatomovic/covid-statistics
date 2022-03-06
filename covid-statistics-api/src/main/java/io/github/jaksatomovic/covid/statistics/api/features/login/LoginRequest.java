package io.github.jaksatomovic.covid.statistics.api.features.login;

import io.github.jaksatomovic.commons.api.messages.request.AbstractRequest;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
public class LoginRequest
    extends AbstractRequest
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    @Override
    protected void appendFields(final StringBuilder sb)
    {
        sb.append("username='").append(username).append('\'');
        sb.append("password='").append(password).append('\'');
    }
}
