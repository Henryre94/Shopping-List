package at.aschowurscht.dev.saadi.erp.backend.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCRUDRepository extends CrudRepository<Product,Integer> {
}
