package at.aschowurscht.dev.saadi.erp.backend.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
