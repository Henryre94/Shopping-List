package at.aschowurscht.dev.saadi.erp.backend.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("select p from Product p where p.vendor.venId = ?1")
    List<Product> findProductByVendor(Integer venId);
}