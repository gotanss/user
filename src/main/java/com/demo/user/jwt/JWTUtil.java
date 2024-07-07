package com.demo.user.jwt;

import static java.time.temporal.ChronoUnit.DAYS;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

@Service
public class JWTUtil {

   private static final String SECRET_KEY = "foobar_123456789_foobar_123456789_foobar_123456789_foobar_123456789";

   public String issueToken(String subject) {
      return issueToken(subject, Map.of());
   }

   public String issueToken(String subject, String... scopes) {
      return issueToken(subject, Map.of("scope", scopes));
   }

   public String issueToken(String subject, Map<String, Object> claims) {
      return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuer("htt://localhost")
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plus(15, DAYS)))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
   }

   private Key getSigningKey() {
      return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
   }
}
