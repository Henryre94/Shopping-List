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
    public List<DemandDTO> increaseQuantity(int proId, int pubId) {
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demands : demandCRUDRepository.findAll()) {
            DemandDTO demandDTO = new DemandDTO();
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() + 1);
                demandCRUDRepository.save(demands);
                demandDTO.setQuantity(demands.getQuantity());
                demandDTO.setName(demands.getProduct().getName());
                demandDTO.setPubName(demands.getPub().getName());
                demandDTOList.add(demandDTO);
            }
        }
        return demandDTOList;
    }
    //Decrease the Quantity of the product in demand
    public List<DemandDTO> decreaseQuantity(int proId, int pubId) {
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demands : demandCRUDRepository.findAll()) {
            DemandDTO demandDTO = new DemandDTO();
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() - 1);
                demandCRUDRepository.save(demands);
                demandDTO.setQuantity(demands.getQuantity());
                demandDTO.setName(demands.getProduct().getName());
                demandDTO.setPubName(demands.getPub().getName());
                demandDTOList.add(demandDTO);
            }
        }
        return demandDTOList;
    }
    //TODO: demandCrudRepository.findAl() groupByProId
    //Create a List of all Products in Demand from a Vendor
    public List<DemandDTO> getAllDemandsFromVendor(int venId) {
        List<DemandDTO> demandDtoList = new ArrayList<>();
        for (Product products : productCRUDRepository.findProductByVendor(venId)) {
            for (Demand demands : demandCRUDRepository.findAll()) {
                DemandDTO demandDto = new DemandDTO();
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
