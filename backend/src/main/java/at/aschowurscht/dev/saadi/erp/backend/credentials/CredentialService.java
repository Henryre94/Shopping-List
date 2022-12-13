package at.aschowurscht.dev.saadi.erp.backend.credentials;

import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {
    final CredentialRepository credentials;

    final PasswordEncoder passwordEncoder;

    final PubCRUDRepository pubCRUDRepository;

    public Credentials byUsername(String username) {
        return credentials.findByUsername(username);
    }

    public Credentials save(String username, String password, Boolean isAdmin, Pub pub) {
        return credentials.save(
                Credentials
                        .builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .isAdmin(isAdmin)
                        .pub(pub)
                        .build()
        );
    }

}
