package at.aschowurscht.dev.saadi.erp.backend.products;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
    private Long productId;

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Id
    public Long getProductId() {
        return productId;
    }
}
