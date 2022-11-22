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

    public void highDemand(int proId){
        for (Demand demands : demandCRUDRepository.findAll()){
            if (demands.getProduct().getProId() == proId){
                demands.setQuantity(demands.getQuantity()+1);
                demandCRUDRepository.save(demands);
            }
        }
    }

    public void lowDemand(int proId){
        for (Demand demands : demandCRUDRepository.findAll()){
            if (demands.getProduct().getProId() == proId){
                demands.setQuantity(demands.getQuantity()-1);
                demandCRUDRepository.save(demands);
            }
        }
    }
}
