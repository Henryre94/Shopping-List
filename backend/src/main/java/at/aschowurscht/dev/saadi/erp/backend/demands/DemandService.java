package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class DemandService {
    @Autowired
    DemandCRUDRepository demandCRUDRepository;

    @Autowired
    ProductCRUDRepository productCRUDRepository;

    public void increaseQuantity(int proId, int pubId) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() + 1);
                demandCRUDRepository.save(demands);
            }
        }
    }

    public void decreaseQuantity(int proId, int pubId) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() - 1);
                demandCRUDRepository.save(demands);
            }
        }
    }

    public Set<DemandDto> getAllDemandsFromVendor(int venId) {
        DemandDto demandDto = new DemandDto();
        Set<DemandDto> demandDtoList = new HashSet<>();
        for (Demand demands : demandCRUDRepository.findAll()) {
            for (Product products : productCRUDRepository.findProductByVendor(venId)) {
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
