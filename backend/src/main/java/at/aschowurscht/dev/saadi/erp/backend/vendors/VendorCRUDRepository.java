package at.aschowurscht.dev.saadi.erp.backend.vendors;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorCRUDRepository extends CrudRepository<Vendor, Integer> {
}
