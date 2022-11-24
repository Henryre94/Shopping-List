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
    //TODO Der letzte Produkt überschreibt alle Produkten gespeichert in der Liste
    public List<DemandDto> getAllDemandsFromVendor(int venId) {
        DemandDto demandDto = new DemandDto();
        List<DemandDto> demandDtoList = new ArrayList<>();
        for (Product products : productCRUDRepository.findProductByVendor(venId)) {
            for (Demand demands : demandCRUDRepository.findAll()) {
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
