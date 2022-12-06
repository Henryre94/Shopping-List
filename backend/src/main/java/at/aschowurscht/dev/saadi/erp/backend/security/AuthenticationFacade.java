package at.aschowurscht.dev.saadi.erp.backend.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

public interface AuthenticationFacade {
    Authentication getAuthentication();
}
