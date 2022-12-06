package at.aschowurscht.dev.saadi.erp.backend.credentials;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder @Getter
public class Credentials {
    @Id
    String username;

    String password;

    Boolean isAdmin;

    @JsonIgnore
    @OneToOne(mappedBy = "credentials")
    Account account;
}

