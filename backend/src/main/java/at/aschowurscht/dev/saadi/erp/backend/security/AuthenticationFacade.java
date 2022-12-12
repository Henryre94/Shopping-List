package at.aschowurscht.dev.saadi.erp.backend.security;

import org.springframework.security.core.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}
