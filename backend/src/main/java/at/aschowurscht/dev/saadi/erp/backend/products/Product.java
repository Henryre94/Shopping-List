package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int proId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

    @OneToMany(mappedBy = "product")
    private List<Demand> pubAssoc = new ArrayList<Demand>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "vendorId")
    private Vendor vendor;

    public Product(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }
}
