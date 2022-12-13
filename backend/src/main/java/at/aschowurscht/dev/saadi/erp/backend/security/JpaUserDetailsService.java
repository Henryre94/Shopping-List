package at.aschowurscht.dev.saadi.erp.backend.security;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.credentials.Credentials;
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
        Credentials credentials = this.credentials.findByUsername(username);
        if (credentials != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            if (credentials.getIsAdmin())
                authorities.add(ADMIN.grant());
            if (!credentials.getIsAdmin())
                authorities.add(PUB.grant());
            return new User(credentials.getUsername(), credentials.getPassword(), authorities);
        }
        throw new UsernameNotFoundException(username);
    }

}