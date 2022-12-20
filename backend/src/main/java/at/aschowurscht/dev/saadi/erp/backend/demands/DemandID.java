package at.aschowurscht.dev.saadi.erp.backend.demands;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DemandID implements Serializable {

    private int product;
    private int pub;

    // This method is used to generate the hash values of objects.
    public int hashCode() {
        return  (product + pub);
    }

    //This method returns true if this object is the same as the obj argument; false otherwise.
    public boolean equals(Object object) {
        if (object instanceof DemandID otherId) {
            return (otherId.product == this.product)
                    && (otherId.pub == this.pub);
        }
        return false;
    }
}
