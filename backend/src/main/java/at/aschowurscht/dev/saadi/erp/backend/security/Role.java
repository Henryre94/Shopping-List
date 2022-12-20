package at.aschowurscht.dev.saadi.erp.backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@Getter
public enum Role {
    PUB("PUB"),
    ADMIN("ADMIN");

    private final String role;

    public GrantedAuthority grant() {
        return new SimpleGrantedAuthority(role);
    }

}
