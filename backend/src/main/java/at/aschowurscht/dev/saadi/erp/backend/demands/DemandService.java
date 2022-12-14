package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DemandService {
    final PubCRUDRepository pubCRUDRepository;
    final DemandCRUDRepository demandCRUDRepository;
    final ProductCRUDRepository productCRUDRepository;


    public DemandDTO createDemand(int proId, int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        DemandDTO demandDTO = new DemandDTO();
        List<Demand> demandList = demandCRUDRepository.findAll();
        if (demandList.isEmpty()) {
            createNewDemand(pub, product, demandDTO);
        }
        if (demandList.size() > 0)
            createDemandDTOFromDemandList(proId, pubId, pub, product, demandDTO);
        return demandDTO;
    }

    private void createDemandDTOFromDemandList(int proId, int pubId, Pub pub, Product product, DemandDTO demandDTO) {
        List<Demand> demandList = demandCRUDRepository
                .findAll()
                .stream()
                .filter(demand -> demand.getPub().getPubId() == pubId && demand.getProduct().getProId() == proId).toList();
        if (demandList.isEmpty())
            createNewDemand(pub, product, demandDTO);
        if (demandList.size() > 0)
            for (Demand demand : demandList) {
                demand.setQuantity(demand.getQuantity() + 1);
                demandCRUDRepository.save(demand);
                demandDTO.setName(demand.getProduct().getName());
                demandDTO.setQuantity(demandCRUDRepository.findAmountOfQuantity(pubId));
                demandDTO.setPubName(demand.getPub().getPubName());
                demandDTO.setProId(demand.getProduct().getProId());
                demandDTO.setPubId(demand.getPub().getPubId());
            }
    }

    private void createNewDemand(Pub pub, Product product, DemandDTO demandDTO) {
        Demand demand = new Demand();
        product.newDemand(demand);

        demand.setProduct(product);

        pub.newDemand(demand);

        demand.setPub(pub);

        demand.setQuantity(1);

        demandCRUDRepository.save(demand);
        pubCRUDRepository.save(pub);
        productCRUDRepository.save(product);

        demandDTO.setName(product.getName());
        demandDTO.setQuantity(demand.getQuantity());
        demandDTO.setPubName(pub.getPubName());
        demandDTO.setProId(product.getProId());
        demandDTO.setPubId(pub.getPubId());
    }

    public List<DemandDTO> decreaseQuantity(int proId, int pubId) {
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demands : demandCRUDRepository.findAll()) {
            DemandDTO demandDTO = new DemandDTO();
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                if (demands.getQuantity() >= 1) {
                    demands.setQuantity(demands.getQuantity() - 1);
                    if (demands.getQuantity() >= 1) {
                        demandCRUDRepository.save(demands);
                        demandDTO.setQuantity(demandCRUDRepository.findAmountOfQuantity(pubId));
                        demandDTO.setName(demands.getProduct().getName());
                        demandDTO.setPubName(demands.getPub().getPubName());
                        demandDTO.setProId(demands.getProduct().getProId());
                        demandDTO.setPubId(demands.getPub().getPubId());
                        demandDTOList.add(demandDTO);
                    } else if (demands.getQuantity() == 0) {
                        demandCRUDRepository.delete(demands);
                    }
                }
            }
        }
        return demandDTOList;
    }

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
                    demandDto.setPubId(demands.getPub().getPubId());
                    demandDtoList.add(demandDto);
                }
            }
        }
        return demandDtoList;
    }

    public void deleteDemand(int proId, int pubId) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demandCRUDRepository.delete(demands);
            }
        }
    }
}
