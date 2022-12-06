package at.aschowurscht.dev.saadi.erp.backend.credentials;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialRepository extends JpaRepository<Credentials, String> {
    Credentials findByUsername(String username);
}

