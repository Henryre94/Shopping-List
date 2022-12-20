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
        Product product = productRepository.findById(proId)
                .orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        DemandDTO demandDTO = new DemandDTO();
        List<Demand> demandList = demandRepository.findAll();

        if (demandList.isEmpty()) {
            createNewDemand(pubId, product, demandDTO);
        }
        if (demandList.size() > 0)
            checkExistenceOfDemand(proId, pubId, product, demandDTO);
        return demandDTO;
    }

    private void checkExistenceOfDemand(int proId, int pubId, Product product, DemandDTO demandDTO) {
        List<Demand> demandList = getDemands(proId, pubId);

        if (demandList.isEmpty()) {
            createNewDemand(pubId, product, demandDTO);
        }
        if (demandList.size() > 0) {
            for (Demand demand : demandList) {
                demand.setQuantity(demand.getQuantity() + 1);
                demandRepository.save(demand);
                fillDemandDTO(proId, demand.getProduct(), demandDTO, demand, demand.getPub());
            }
        }
    }

    private List<Demand> getDemands(int proId, int pubId) {
        return demandRepository
                .findAll()
                .stream()
                .filter(demand -> demand.getPub().getPubId() == pubId && demand.getProduct().getProId() == proId)
                .toList();
    }

    private void createNewDemand(int pubId, Product product, DemandDTO demandDTO) {
        Demand demand = new Demand();
        Pub pub = pubRepository.findById(pubId)
                .orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));

        product.newDemand(demand);
        demand.setProduct(product);
        pub.newDemand(demand);
        demand.setPub(pub);
        demand.setQuantity(1);

        demandRepository.save(demand);
        pubRepository.save(pub);
        productRepository.save(product);

        fillDemandDTO(product.getProId(), product, demandDTO, demand, pub);
    }

    private void fillDemandDTO(int proId, Product product, DemandDTO demandDTO, Demand demand, Pub pub) {
        demandDTO.setName(product.getName());
        demandDTO.setQuantity(demandRepository.findAmountOfQuantity(proId));
        demandDTO.setPubName(pub.getPubName());
        demandDTO.setProId(product.getProId());
        demandDTO.setPubId(pub.getPubId());
        demandDTO.setVenId(demand.getProduct().getVendor().getVenId());
    }

    public List<DemandDTO> decreaseQuantity(int proId, int pubId) {
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demand : getDemands(proId, pubId)) {
            DemandDTO demandDTO = new DemandDTO();
            if (demand.getQuantity() >= 1) {
                demand.setQuantity(demand.getQuantity() - 1);
                demandRepository.save(demand);
                fillDemandDTO(proId, demand.getProduct(), demandDTO, demand, demand.getPub());
                demandDTOList.add(demandDTO);
            } else {
                demandRepository.delete(demand);
            }
        }
        return demandDTOList;
    }

    public List<DemandDTO> getAllDemandsFromVendor(int venId) {
        List<DemandDTO> demandDtoList = new ArrayList<>();
        for (Product products : productRepository.findProductByVendor(venId)) {
            for (Demand demand : demandRepository.findAll()) {
                DemandDTO demandDTO = new DemandDTO();
                if (demand.getProduct().getProId() == products.getProId()) {
                    fillDemandDTO(demand.getProduct().getProId(), demand.getProduct(), demandDTO, demand, demand.getPub());
                    demandDtoList.add(demandDTO);
                }
            }
        }
        return demandDtoList;
    }

    public void deleteDemand(int proId, int pubId) {
        demandRepository.deleteAll(getDemands(proId, pubId));
    }
}
