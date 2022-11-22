package at.aschowurscht.dev.saadi.erp.backend.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductCRUDRepository extends JpaRepository<Product,Integer> {

    @Query(value = "select p.name, sum(d.quantity) from Product p left join Vendor v on p.vendor.venId = v.venId join Demand d on p.proId = d.product.proId where v.venId = ?1")
    List<Product> findProductsInDemandFromVendor(Integer venId);


}
