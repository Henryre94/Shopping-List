package at.aschowurscht.dev.saadi.erp.backend.credentials;

import at.aschowurscht.dev.saadi.erp.backend.dtos.CredentialsDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialsService {
    private final CredentialRepository credentials;
    private final PasswordEncoder passwordEncoder;

    public CredentialsDTO getCredentials(Authentication authentication) {
        Credential credentialsByUsername = credentials.findByUsername(authentication.getName());
        Integer pubId;
        if (credentialsByUsername.getPub() != null) {
            pubId = credentialsByUsername.getPub().getPubId();
        } else {
            pubId = null;
        }
        return new CredentialsDTO(pubId, credentialsByUsername.getUsername(), credentialsByUsername.getIsAdmin());
    }

    public void save(String username, String password, Boolean isAdmin, Pub pub) {
        credentials.save(
                Credential
                        .builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .isAdmin(isAdmin)
                        .pub(pub)
                        .build()
        );
    }
}
