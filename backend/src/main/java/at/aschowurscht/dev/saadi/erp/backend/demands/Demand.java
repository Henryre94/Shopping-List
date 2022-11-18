package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name= "product_demand")
@IdClass(DemandID.class)
public class Demand{
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "proId")
    private Product product;

    @Id
    @ManyToOne
    @JoinColumn(name = "pub_id", referencedColumnName = "pubId")
    private Pub pub;

    @Column(name = "quantity")
    private int quantity;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {this.product = product;}
    public Pub getPub() {
        return pub;
    }
    public void setPub(Pub pub) {this.pub = pub;}

}
