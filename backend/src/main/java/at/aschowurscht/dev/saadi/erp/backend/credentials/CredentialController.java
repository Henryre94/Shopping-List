package at.aschowurscht.dev.saadi.erp.backend.credentials;

import at.aschowurscht.dev.saadi.erp.backend.dtos.CredentialsDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credentials")
@CrossOrigin
@RequiredArgsConstructor
public class CredentialController {
    final CredentialService credentialService;

    @GetMapping
    public CredentialsDTO getCredentials(Authentication authentication){
        return credentialService.getCredentials(authentication);
    }
}
