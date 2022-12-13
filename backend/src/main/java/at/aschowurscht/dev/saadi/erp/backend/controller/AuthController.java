package at.aschowurscht.dev.saadi.erp.backend.controller;


import at.aschowurscht.dev.saadi.erp.backend.dtos.AuthorizationDTO;
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

    @GetMapping
    public AuthorizationDTO generate(Authentication authentication) {
        return tokens.generate(authentication);
    }

}
