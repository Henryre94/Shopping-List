package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int proId;

    @Column(nullable = false)
    private String name;

    private String unit;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    @JsonIgnore
    private List<Demand> pubWithDemands = new ArrayList<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "venId")
    private Vendor vendor;

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public void newDemand(Demand demand) {
        this.pubWithDemands.add(demand);
    }
}
