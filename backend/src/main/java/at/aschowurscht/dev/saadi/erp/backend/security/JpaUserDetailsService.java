package at.aschowurscht.dev.saadi.erp.backend.security;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.credentials.Credential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static at.aschowurscht.dev.saadi.erp.backend.security.Role.ADMIN;
import static at.aschowurscht.dev.saadi.erp.backend.security.Role.PUB;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {
    final CredentialRepository credentials;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = this.credentials.findByUsername(username);
        if (credential != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (credential.getIsAdmin())
                authorities.add(ADMIN.grant());
            if (!credential.getIsAdmin())
                authorities.add(PUB.grant());
            return new User(credential.getUsername(), credential.getPassword(), authorities);
        }
        throw new UsernameNotFoundException(username);
    }

}
