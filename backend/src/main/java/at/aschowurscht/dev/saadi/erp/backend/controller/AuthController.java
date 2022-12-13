package at.aschowurscht.dev.saadi.erp.backend.controller;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialService;
import at.aschowurscht.dev.saadi.erp.backend.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/token")
@RequiredArgsConstructor
public class AuthController {
    private final TokenService tokens;
    private final CredentialService credentials;

    @GetMapping
    public String generate(Authentication authentication) {
        return tokens.generate(authentication);
    }

}
