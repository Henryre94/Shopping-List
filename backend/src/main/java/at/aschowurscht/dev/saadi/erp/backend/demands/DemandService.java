package at.aschowurscht.dev.saadi.erp.backend.demands;


import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DemandService {
    final PubRepository pubRepository;
    final DemandRepository demandRepository;
    final ProductRepository productRepository;


    public DemandDTO createDemand(int proId, int pubId) {
        Product product = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        DemandDTO demandDTO = new DemandDTO();
        List<Demand> demandList = demandRepository.findAll();
        if (demandList.isEmpty()) {
            createNewDemand(pubId, product,demandDTO);
        }
        if (demandList.size() > 0)
            checkExistenceOfDemand(proId, pubId, product,demandDTO);
        return demandDTO;
    }

    private void checkExistenceOfDemand(int proId, int pubId, Product product, DemandDTO demandDTO) {
        List<Demand> demandList = demandRepository
                .findAll()
                .stream()
                .filter(demand -> demand.getPub().getPubId() == pubId && demand.getProduct().getProId() == proId).toList();
        if (demandList.isEmpty())
            createNewDemand(pubId, product,demandDTO);
        if (demandList.size() > 0)
            for (Demand demand : demandList) {
                demand.setQuantity(demand.getQuantity() + 1);
                demandRepository.save(demand);
                fillDemandDTO(pubId, demand.getProduct(), demandDTO, demand, demand.getPub());
            }
    }

    private void createNewDemand(int pubId, Product product, DemandDTO demandDTO) {
        Demand demand = new Demand();
        Pub pub = pubRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        product.newDemand(demand);

        demand.setProduct(product);

        pub.newDemand(demand);

        demand.setPub(pub);

        demand.setQuantity(1);

        demandRepository.save(demand);
        pubRepository.save(pub);
        productRepository.save(product);

        fillDemandDTO(pubId, product, demandDTO, demand, pub);
    }

    private void fillDemandDTO(int pubId, Product product, DemandDTO demandDTO, Demand demand, Pub pub) {
        demandDTO.setName(product.getName());
        demandDTO.setQuantity(demandRepository.findAmountOfQuantity(pubId));
        demandDTO.setPubName(pub.getPubName());
        demandDTO.setProId(product.getProId());
        demandDTO.setPubId(pub.getPubId());
        demandDTO.setVenId(demand.getProduct().getVendor().getVenId());
    }

    public List<DemandDTO> decreaseQuantity(int proId, int pubId) {
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demands : demandRepository.findAll()) {
            DemandDTO demandDTO = new DemandDTO();
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                if (demands.getQuantity() >= 1) {
                    demands.setQuantity(demands.getQuantity() - 1);
                    if (demands.getQuantity() >= 1) {
                        demandRepository.save(demands);
                        fillDemandDTO(pubId,demands.getProduct(),demandDTO,demands,demands.getPub());
                        demandDTOList.add(demandDTO);
                    } else if (demands.getQuantity() == 0) {
                        demandRepository.delete(demands);
                    }
                }
            }
        }
        return demandDTOList;
    }

    public List<DemandDTO> getAllDemandsFromVendor(int venId) {
        List<DemandDTO> demandDtoList = new ArrayList<>();
        for (Product products : productRepository.findProductByVendor(venId)) {
            for (Demand demands : demandRepository.findAll()) {
                DemandDTO demandDTO = new DemandDTO();
                if (demands.getProduct().getProId() == products.getProId()) {
                    fillDemandDTO(demands.getPub().getPubId(),demands.getProduct(),demandDTO,demands,demands.getPub());
                    demandDtoList.add(demandDTO);
                }
            }
        }
        return demandDtoList;
    }

    public void deleteDemand(int proId,int pubId) {
        for (Demand demands : demandRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demandRepository.delete(demands);
            }
        }
    }
}
