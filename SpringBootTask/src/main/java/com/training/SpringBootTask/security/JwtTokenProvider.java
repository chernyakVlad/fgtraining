package com.training.SpringBootTask.security;

import com.training.SpringBootTask.services.impl.TokenStoreImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider implements Serializable {

    @Autowired
    TokenStoreImpl tokenStore;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityJwtConstants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(SecurityJwtConstants.AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SecurityJwtConstants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityJwtConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        return  Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(SignatureAlgorithm.HS256, SecurityJwtConstants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityJwtConstants.REFRESH_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        tokenStore.checkToken(token);
        final String userName = getUsernameFromToken(token);
        boolean test = userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
        return test;
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuthentication, UserDetails userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(SecurityJwtConstants.SIGNING_KEY);
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();
        final Collection<? extends  GrantedAuthority> authorities = Arrays.stream(claims.get(SecurityJwtConstants.AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String aut, UserDetails userDetails) {

        final Collection<? extends  GrantedAuthority> authorities = Arrays.stream(aut.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }
}
