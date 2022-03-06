package io.github.jaksatomovic.covid.statistics.core.security;

import io.github.jaksatomovic.commons.api.validation.Check;
import io.github.jaksatomovic.covid.statistics.core.utility.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class JwtAuthenticationFilter
    extends OncePerRequestFilter
{
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private JwtTokenUtility jwtTokenUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        String header = req.getHeader(Constants.HEADER_STRING);

        String username  = null;
        String authToken = null;

        if (Check.notNull(header) && header.startsWith(Constants.TOKEN_PREFIX))
        {

            try
            {
                authToken = header.replace(Constants.TOKEN_PREFIX, "");
                username = jwtTokenUtility.getUsernameFromToken(authToken);
            }
            catch (IllegalArgumentException e)
            {
                logger.error("an error occured during getting username from token", e);
            }
            catch (ExpiredJwtException e)
            {
                logger.warn("the token is expired and not valid anymore", e);
            }
            catch (SignatureException e)
            {
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        }
        else
        {
            logger.warn("couldn't find bearer string, will ignore the header");
        }

        if (Check.notNull(username) && Check.notNull(SecurityContextHolder.getContext().getAuthentication()))
        {

            UserDetails userDetails = loadUserByUsername(username);

            if (jwtTokenUtility.validateToken(authToken, userDetails))
            {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN)));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(req, res);
    }

    private UserDetails loadUserByUsername(final String username)
    {
        if (Constants.USERNAME.equalsIgnoreCase(username))
        {
            return new UserDetails()
            {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities()
                {
                    return Collections.singletonList(new SimpleGrantedAuthority(ROLE_ADMIN));
                }

                @Override
                public String getPassword()
                {
                    return null;
                }

                @Override
                public String getUsername()
                {
                    return Constants.USERNAME;
                }

                @Override
                public boolean isAccountNonExpired()
                {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked()
                {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired()
                {
                    return true;
                }

                @Override
                public boolean isEnabled()
                {
                    return true;
                }
            };
        }

        return null;
    }
}
