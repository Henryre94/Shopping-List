package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandService {
    @Autowired
    DemandCRUDRepository demandCRUDRepository;

    @Autowired
    ProductCRUDRepository productCRUDRepository;

    public void increaseQuantity(int proId, int pubId){
        for (Demand demands : demandCRUDRepository.findAll()){
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId){
                demands.setQuantity(demands.getQuantity()+1);
                demandCRUDRepository.save(demands);
            }
        }
    }
    public void decreaseQuantity(int proId, int pubId){
        for (Demand demands : demandCRUDRepository.findAll()){
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId ){
                demands.setQuantity(demands.getQuantity()-1);
                demandCRUDRepository.save(demands);
            }
        }
    }
}
