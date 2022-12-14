package at.aschowurscht.dev.saadi.erp.backend.credentials;

import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor @Builder @Getter
public class Credential {

    @Id
    String username;

    String password;

    Boolean isAdmin;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pubId", referencedColumnName = "pubId")
    Pub pub;

    public Credential(String username, String password, Boolean isAdmin, Pub pub) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.pub = pub;
    }
}

