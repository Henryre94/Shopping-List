package at.aschowurscht.dev.saadi.erp.backend.credentials;

import at.aschowurscht.dev.saadi.erp.backend.dtos.CredentialsDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CredentialService {
    final CredentialRepository credentials;
    final PasswordEncoder passwordEncoder;

    final PubRepository pubRepository;

    public CredentialsDTO getCredentials(Authentication authentication ){
        Credential credential1 = credentials.findByUsername(authentication.getName());
        Integer pubId;
        if (credential1.getPub() != null){
            pubId = credential1.getPub().getPubId();
        }else{
            pubId = null;
        }
       return new CredentialsDTO(pubId, credential1.getUsername(), credential1.getIsAdmin());
    }

    public Credential byUsername(String username) {
        return credentials.findByUsername(username);
    }

    public Credential save(String username, String password, Boolean isAdmin, Pub pub) {
        return credentials.save(
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
