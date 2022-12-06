package at.aschowurscht.dev.saadi.erp.backend.credentials;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {
    final CredentialRepository credentials;

    final PasswordEncoder passwordEncoder;

    public Credentials byUsername(String username) {
        return credentials.findByUsername(username);
    }

    public Credentials save(String username, String password, Boolean isAdmin) {
        return credentials.save(
                Credentials
                        .builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .isAdmin(isAdmin)
                        .build()
        );
    }

}
