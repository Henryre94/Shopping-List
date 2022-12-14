package at.aschowurscht.dev.saadi.erp.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    String name;
    String unit;
    int proId;
}
