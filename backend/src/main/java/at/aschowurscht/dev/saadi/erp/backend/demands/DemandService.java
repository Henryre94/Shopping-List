package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandService {
    @Autowired
    DemandCRUDRepository demandCRUDRepository;

    @Autowired
    ProductCRUDRepository productCRUDRepository;

    public void post(Demand demand, int proId){
        Demand newDemand = new Demand();
        Product product = productCRUDRepository.findById(proId).get();
        newDemand.setProduct(product);
        demandCRUDRepository.save(newDemand);
    }
}
