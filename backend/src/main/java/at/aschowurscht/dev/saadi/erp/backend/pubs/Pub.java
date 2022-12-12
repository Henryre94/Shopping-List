package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.credentials.Credentials;
import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "pubs")
public class Pub {

    @OneToOne
    @JoinColumn(name = "credentials", referencedColumnName = "username")
    private Credentials credentials;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pubId;

    @Column(nullable = false)
    private String pubName;

    @OneToMany(mappedBy = "pub")
    @JsonManagedReference
    @JsonIgnore
    private List<Demand> productsOnDemand = new ArrayList<>();

    public Pub(String pubName,Credentials credentials) {
        this.pubName = pubName;
        this.credentials = credentials;
    }

    public void newDemand(Demand demand) {
        this.productsOnDemand.add(demand);
    }

    public void removeDemand(Demand demand) {
        this.productsOnDemand.remove(demand);
    }
}