package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PubCRUDRepository extends CrudRepository<Pub, UUID> {
}
