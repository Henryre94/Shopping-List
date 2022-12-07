package at.aschowurscht.dev.saadi.erp.backend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemandDTO {
    int quantity;
    int proId;
    String name;
    int pubId;
    String pubName;
}
