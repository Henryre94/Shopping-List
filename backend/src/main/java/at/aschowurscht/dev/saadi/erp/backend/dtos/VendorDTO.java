package at.aschowurscht.dev.saadi.erp.backend.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VendorDTO {
    String name;
    String address;
    int venId;
}