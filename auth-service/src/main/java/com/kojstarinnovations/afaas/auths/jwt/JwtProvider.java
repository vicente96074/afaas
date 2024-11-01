package com.kojstarinnovations.afaas.auths.jwt;

import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * JwtProvider
 *
 * @author Augusto Vicente
 */
@Component
public class JwtProvider {

    /**
     * Generate token
     *
     * @param authentication Authentication object with user data
     * @return String token
     */
    public String generateToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        List<String> roles = principalUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> accesses = principalUser.getAccesses().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim("id", principalUser.getId())
                .claim("roles", roles)
                .claim("credentials", principalUser.getCredentials())
                .claim("accesses", accesses)
                .claim("storeBranchId", principalUser.getStoreBranchId())
                .claim("storeId", principalUser.getStoreId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration * 60L * 1000L)) // jwtExpiration = 1, token expira en 1 minuto
                .signWith(getSecret(secret))
                .compact();
    }

    /**
     * Get username from token
     *
     * @param token String token
     * @return String username
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecret(secret))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Method to validate token
     *
     * @param token String token
     * @return boolean
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            Logger.getLogger("Malformed JWT Token").log(Level.SEVERE, "Token mal formado {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            Logger.getLogger("Unsupported JWT Token").log(Level.SEVERE, "Token no soportado {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            Logger.getLogger("Expired JWT Token").log(Level.SEVERE, "Token expirado {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            Logger.getLogger("JWT claims vacío").log(Level.SEVERE, "JWT claims vacío {}", ex.getMessage());
        } catch (SignatureException ex) {
            Logger.getLogger("JWT signature fail").log(Level.SEVERE, "JWT signature fallo {}", ex.getMessage());
        }

        return false;
    }

    /**
     * Method to get secret
     *
     * @param secret String secret
     * @return Key object
     */
    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;
}