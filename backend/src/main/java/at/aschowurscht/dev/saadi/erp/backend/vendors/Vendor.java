package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vendors")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int venId;

    @Column(nullable = false)
    private String name;

    private String address;

    @OneToMany(mappedBy = "vendor")
    @JsonManagedReference
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Vendor(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
