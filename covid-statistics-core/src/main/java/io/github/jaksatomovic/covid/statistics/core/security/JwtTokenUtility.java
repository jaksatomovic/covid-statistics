package io.github.jaksatomovic.covid.statistics.core.security;

import io.github.jaksatomovic.covid.statistics.core.utility.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.function.Function;

/**
 * @author Jakša Tomović
 * @since 1.0
 */
@Service
public class JwtTokenUtility
    implements Serializable
{

    public String getUsernameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser()
                   .setSigningKey(Constants.SIGNING_KEY)
                   .parseClaimsJws(token)
                   .getBody();
    }

    private Boolean isTokenExpired(String token)
    {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken()
    {
        return doGenerateToken(Constants.USERNAME);
    }

    private String doGenerateToken(String subject)
    {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuer("http://io.github.jaksatomovic")
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                   .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
                   .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = getUsernameFromToken(token);
        return (
            username.equals(Constants.USERNAME)
                && !isTokenExpired(token));
    }

}
