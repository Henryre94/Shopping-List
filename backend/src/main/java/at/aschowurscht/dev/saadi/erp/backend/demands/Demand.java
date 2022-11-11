package at.aschowurscht.dev.saadi.erp.backend.demands;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Demand {
    private Long demandId;

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    @Id
    public Long getDemandId() {
        return demandId;
    }
}
