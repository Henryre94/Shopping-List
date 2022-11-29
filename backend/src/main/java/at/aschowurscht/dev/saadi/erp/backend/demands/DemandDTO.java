package at.aschowurscht.dev.saadi.erp.backend.demands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemandDTO {
    private int quantity;
    private String name;
    private String pubName;
}
