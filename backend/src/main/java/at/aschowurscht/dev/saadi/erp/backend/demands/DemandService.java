package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class DemandService {
    @Autowired
    DemandCRUDRepository demandCRUDRepository;

    @Autowired
    ProductCRUDRepository productCRUDRepository;

    //Increase the Quantity of the product on demand
    public void increaseQuantity(int proId, int pubId) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() + 1);
                demandCRUDRepository.save(demands);
            }
        }
    }
    //Decrease the Quantity of the product in demand
    public void decreaseQuantity(int proId, int pubId) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() - 1);
                demandCRUDRepository.save(demands);
            }
        }
    }
    //TODO: demandCrudRepository.findAl() groupByProId
    //Create a List of all Products in Demand from a Vendor
    public List<DemandDto> getAllDemandsFromVendor(int venId) {
        List<DemandDto> demandDtoList = new ArrayList<>();
        for (Product products : productCRUDRepository.findProductByVendor(venId)) {
            for (Demand demands : demandCRUDRepository.findAll()) {
                DemandDto demandDto = new DemandDto();
                if (demands.getProduct().getProId() == products.getProId()) {
                    demandDto.setName(products.getName());
                    demandDto.setQuantity(demandCRUDRepository.findAmountOfQuantity(demands.getProduct().getProId()));
                    demandDtoList.add(demandDto);
                }
            }
        }
        return demandDtoList;
    }
}
