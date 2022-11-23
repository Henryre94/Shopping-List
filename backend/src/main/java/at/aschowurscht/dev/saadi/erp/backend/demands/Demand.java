package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "demands")
@IdClass(DemandID.class)
public class Demand {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private Product product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubId", referencedColumnName = "pubId")
    private Pub pub;

    @Column(name = "quantity")
    private int quantity;

}
