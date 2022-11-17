package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name= "product_demand")
public class Demand implements Serializable {

    @EmbeddedId
    private DemandID id;

    @ManyToOne
    @MapsId("proId")
    @JoinColumn(name = "product_id", referencedColumnName = "proId")
    private Product product;


    @ManyToOne
    @MapsId("pubId")
    @JoinColumn(name = "pub_id", referencedColumnName = "pubId")
    private Pub pub;

    @Column(name = "quantity")
    private int quantity;

}
