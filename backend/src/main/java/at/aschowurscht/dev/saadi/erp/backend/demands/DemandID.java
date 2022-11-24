package at.aschowurscht.dev.saadi.erp.backend.demands;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DemandID implements Serializable {

    private int product;
    private int pub;

    public int hashCode() {
        return (int) (product + pub);
    }

    public boolean equals(Object object) {
        if (object instanceof DemandID) {
            DemandID otherId = (DemandID) object;
            return (otherId.product == this.product)
                    && (otherId.pub == this.pub);
        }
        return false;
    }


}
