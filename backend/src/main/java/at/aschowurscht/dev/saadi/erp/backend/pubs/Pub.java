package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
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

public class Pub {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pubId;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "pub")
    private List<Demand> productAssoc = new ArrayList<>();

    public Pub(String name) {
        this.name = name;
    }
    
    public void newDemand(Demand demand){
        this.productAssoc.add(demand);
    }

    public void removeDemand(Demand demand){this.productAssoc.remove(demand);}
}