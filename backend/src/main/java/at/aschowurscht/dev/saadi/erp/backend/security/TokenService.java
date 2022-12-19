package at.aschowurscht.dev.saadi.erp.backend.security;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.credentials.Credential;
import at.aschowurscht.dev.saadi.erp.backend.dtos.AuthorizationDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.CredentialsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final JwtEncoder encoder;
    private final CredentialRepository credentials;

    public AuthorizationDTO generate(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        Credential credential1 = credentials.findByUsername(authentication.getName());
        Integer pubId;
        if (credential1.getPub() != null){
            pubId = credential1.getPub().getPubId();
        }else{
            pubId = null;
        }
        CredentialsDTO credentialsDTO = new CredentialsDTO(pubId, credential1.getUsername(), credential1.getIsAdmin());
        String tokenValue = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new AuthorizationDTO(tokenValue,credentialsDTO);
    }
}



