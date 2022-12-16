package at.aschowurscht.dev.saadi.erp.backend.credentials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, String> {
    Credential findByUsername(String username);
}

