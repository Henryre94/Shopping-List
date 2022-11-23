package at.aschowurscht.dev.saadi.erp.backend.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductCRUDRepository extends JpaRepository<Product,Integer> {

}
