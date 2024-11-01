package com.kojstarinnovations.afaas.us.jwt;

import com.kojstarinnovations.afaas.commons.data.dto.PrincipalUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            Logger.getLogger("Malformed JWT Token").log(Level.SEVERE, "Token mal formado {}", ex);
        } catch (UnsupportedJwtException ex) {
            Logger.getLogger("Unsupported JWT Token").log(Level.SEVERE, "Token no soportado {}", ex);
        } catch (ExpiredJwtException ex) {
            Logger.getLogger("Expired JWT Token").log(Level.SEVERE, "Token expirado {}", ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger("JWT claims vacío").log(Level.SEVERE, "JWT claims vacío {}", ex);
        } catch (SignatureException ex) {
            Logger.getLogger("JWT signature fail").log(Level.SEVERE, "JWT signature fallo {}", ex);
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Extrae el token, que sigue a "Bearer "
        }
        return null;
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecret(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsFromToken(token);

        PrincipalUser principalUser = new PrincipalUser(
                (String) claims.get("id"),
                (String) claims.get("storeBranchId"),
                (String) claims.get("storeId"),
                (String) claims.get("credentials"),
                claims.getSubject(),  // username
                (String) claims.get("email"),
                null,  // La contraseña no se necesita aquí
                extractAuthorities(claims),
                extractAccesses(claims)
        );

        return new UsernamePasswordAuthenticationToken(principalUser, null, principalUser.getAuthorities());
    }

    private Key getSecret(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    private List<GrantedAuthority> extractAuthorities(Claims claims) {
        return ((List<String>) claims.get("roles")).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private List<GrantedAuthority> extractAccesses(Claims claims) {
        return ((List<String>) claims.get("accesses")).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
