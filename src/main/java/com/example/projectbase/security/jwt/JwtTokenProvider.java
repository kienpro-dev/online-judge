package com.example.projectbase.security.jwt;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.exception.InvalidException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

  private final String CLAIM_TYPE = "type";
  private final String TYPE_ACCESS = "access";
  private final String TYPE_REFRESH = "refresh";
  private final String USERNAME_KEY = "username";
  private final String AUTHORITIES_KEY = "auth";

  @Value("${jwt.secret}")
  private String SECRET_KEY;

  @Value("${jwt.access.expiration_time}")
  private Integer EXPIRATION_TIME_ACCESS_TOKEN;

  @Value("${jwt.refresh.expiration_time}")
  private Integer EXPIRATION_TIME_REFRESH_TOKEN;

  public String generateToken(UserPrincipal userPrincipal, Boolean isRefreshToken) {
    String authorities = userPrincipal.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.joining(","));
    Map<String, Object> claim = new HashMap<>();
    claim.put(CLAIM_TYPE, isRefreshToken ? TYPE_REFRESH : TYPE_ACCESS);
    claim.put(USERNAME_KEY, userPrincipal.getUsername());
    claim.put(AUTHORITIES_KEY, authorities);
    if (isRefreshToken) {
      return Jwts.builder()
          .setClaims(claim)
          .setIssuedAt(new Date(System.currentTimeMillis()))
          .setExpiration(new Date(System.currentTimeMillis() + (EXPIRATION_TIME_REFRESH_TOKEN * 60 * 1000L)))
          .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
          .compact();
    }
    return Jwts.builder()
        .setClaims(claim)
        .setSubject(userPrincipal.getId())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + (EXPIRATION_TIME_ACCESS_TOKEN * 60 * 1000L)))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public Authentication getAuthenticationByRefreshToken(String refreshToken) {
    Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(refreshToken).getBody();
    if (!claims.get(CLAIM_TYPE).equals(TYPE_REFRESH) || ObjectUtils.isEmpty(claims.get(AUTHORITIES_KEY))
        || ObjectUtils.isEmpty(claims.get(USERNAME_KEY))) {
      throw new InvalidException(ErrorMessage.Auth.INVALID_REFRESH_TOKEN);
    }
    Collection<? extends GrantedAuthority> authorities =
        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    UserDetails principal = new UserPrincipal(claims.get(USERNAME_KEY).toString(), "", authorities);
    return new UsernamePasswordAuthenticationToken(principal, "", authorities);
  }

  public String extractClaimUsername(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().get(USERNAME_KEY).toString();
  }

  public String extractSubjectFromJwt(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
  }

  public Date extractExpirationFromJwt(String token) {
    return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration();
  }

  public Boolean isTokenExpired(String token) {
    return extractExpirationFromJwt(token).before(new Date());
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      log.error("Invalid JWT signature");
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty");
    }
    return false;
  }

}
