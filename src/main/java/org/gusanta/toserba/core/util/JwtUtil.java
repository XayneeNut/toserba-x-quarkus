package org.gusanta.toserba.core.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class JwtUtil {
    public static String generateToken(String email) {
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime expiredDate = today.plusMonths(1);
        long expiredDateInMillis = expiredDate.toInstant(ZoneOffset.UTC).toEpochMilli();
        return Jwt
                .issuer("gusanta-jwt")
                .groups("user")
                .expiresAt(expiredDateInMillis).sign();
    }

    public static String generateToken() {
        return Jwt.issuer("gusanta-jwt").subject("gusanta-jwt").groups("user")
                .expiresAt(System.currentTimeMillis() + 3600).sign();
    }
}