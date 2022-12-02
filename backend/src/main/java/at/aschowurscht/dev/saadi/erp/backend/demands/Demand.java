package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "demands")
@IdClass(DemandID.class)
public class Demand {
    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "proId", referencedColumnName = "proId")
    private Product product;

    @Id
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "pubId", referencedColumnName = "pubId")
    private Pub pub;

    @Column(name = "quantity")
    private int quantity;

}
