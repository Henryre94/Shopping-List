package at.aschowurscht.dev.saadi.erp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorizationDTO {
    String token;
    CredentialsDTO credentialsDTO;
}
