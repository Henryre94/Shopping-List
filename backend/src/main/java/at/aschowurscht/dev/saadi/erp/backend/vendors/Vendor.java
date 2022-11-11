package at.aschowurscht.dev.saadi.erp.backend.vendors;

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
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int venId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;



}
