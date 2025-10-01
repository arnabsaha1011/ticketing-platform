package com.arnab_saha.tickets.utils;

import com.arnab_saha.tickets.domain.entities.UserRole;
import com.arnab_saha.tickets.exceptions.TicketException;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class JwtUtils {
    private JwtUtils() {
    }

    public static UUID parseUserId(Jwt jwt) {
        return UUID.fromString(jwt.getSubject());
    }

    public static UserRole parseUserRole(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (null == realmAccess || !realmAccess.containsKey("roles")) {
            throw new TicketException("No role associated with the user");
        }
        @SuppressWarnings("unchecked")
        List<String> roles = (List<String>) realmAccess.get("roles");

        return roles.stream()
                .filter(role -> role.startsWith("ROLE"))
                .findFirst()
                .map(UserRole::valueOf)
                .orElseThrow(() -> new TicketException("No role associated with the user"));
    }
}
