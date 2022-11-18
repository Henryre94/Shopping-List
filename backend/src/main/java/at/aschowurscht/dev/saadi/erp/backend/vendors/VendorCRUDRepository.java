package at.aschowurscht.dev.saadi.erp.backend.vendors;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendorCRUDRepository extends CrudRepository<Vendor, Integer> {
}
