package at.aschowurscht.dev.saadi.erp.backend.products;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int proId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String unit;

}
