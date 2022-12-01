package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
@RequiredArgsConstructor
public class DemandService {
    final DemandCRUDRepository demandCRUDRepository;
    final ProductCRUDRepository productCRUDRepository;

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
                demandDTO.setPubName(demands.getPub().getPubName());
                demandDTO.setProId(demands.getProduct().getProId());
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
                demandDTO.setPubName(demands.getPub().getPubName());
                demandDTO.setProId(demands.getProduct().getProId());
                demandDTOList.add(demandDTO);
            }
        }
        return demandDTOList;
    }
    //Create a List of all Products in Demand from a Vendor
    public List<DemandDTO> getAllDemandsFromVendor(int venId) {
        List<DemandDTO> demandDtoList = new ArrayList<>();
        for (Product products : productCRUDRepository.findProductByVendor(venId)) {
            for (Demand demands : demandCRUDRepository.findAll()) {
                DemandDTO demandDto = new DemandDTO();
                if (demands.getProduct().getProId() == products.getProId()) {
                    demandDto.setName(products.getName());
                    demandDto.setQuantity(demands.getQuantity());
                    demandDto.setPubName(demands.getPub().getPubName());
                    demandDto.setProId(demands.getProduct().getProId());
                    demandDtoList.add(demandDto);
                }
            }
        }
        return demandDtoList;
    }
}
