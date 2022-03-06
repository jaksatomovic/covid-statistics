package io.github.jaksatomovic.covid.statistics.web.controller;

import io.github.jaksatomovic.commons.api.validation.Defense;
import io.github.jaksatomovic.covid.statistics.api.features.login.LoginRequest;
import io.github.jaksatomovic.covid.statistics.api.shared.AuthToken;
import io.github.jaksatomovic.covid.statistics.core.security.JwtTokenUtility;
import io.github.jaksatomovic.covid.statistics.core.utility.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * THIS IS QUICK AND DIRTY WAY TO IMPLEMENT JWT TOKEN AUTHENTICATION.
 * WE DON'T USE DB ENTITIES AND USERNAME AND PASSWORD IS HARDCODED.
 *
 * @author Jakša Tomović
 * @since 1.0
 */
@CrossOrigin (origins = "*",
              maxAge = 3600)
@RestController
@RequestMapping ("/token")
public class AuthenticationController
{

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtility       jwtTokenUtility;

    public AuthenticationController(final AuthenticationManager authenticationManager, final JwtTokenUtility jwtTokenUtility)
    {
        this.authenticationManager = Defense.notNull(authenticationManager, AuthenticationManager.class.getSimpleName());
        this.jwtTokenUtility = Defense.notNull(jwtTokenUtility, JwtTokenUtility.class.getSimpleName());
    }

    @RequestMapping (value = "/generate",
                     method = RequestMethod.POST)
    public ResponseEntity<AuthToken> register(@RequestBody LoginRequest request)
        throws AuthenticationException
    {
        if (Constants.USERNAME.equalsIgnoreCase(request.getUsername()) && Constants.PASSWORD.equalsIgnoreCase(request.getPassword()))
        {
            final String token = jwtTokenUtility.generateToken();
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AuthToken(token, request.getUsername()));
        }

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST).build();
    }

}
