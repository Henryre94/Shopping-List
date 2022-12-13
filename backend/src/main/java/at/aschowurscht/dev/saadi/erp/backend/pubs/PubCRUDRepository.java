package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface PubCRUDRepository extends JpaRepository<Pub, Integer> {
    Pub findPubByPubName(String pubName);
}
