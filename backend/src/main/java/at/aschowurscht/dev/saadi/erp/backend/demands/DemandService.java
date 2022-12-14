package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.credentials.Credential;
import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubRepository;
import at.aschowurscht.dev.saadi.erp.backend.security.AuthenticationFacade;
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
    final CredentialRepository credentialRepository;
    final AuthenticationFacade authenticationFacade;

    private Pub getPub() {
        Credential credential = credentialRepository.findByUsername(authenticationFacade.getAuthentication().getName());
        return credential.getPub();
    }

    public DemandDTO createDemand(int proId) {
        Pub pub = (Pub) getPub();
        int pubId = pub.getPubId();
        Product product = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
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
        List<Demand> demandList = demandRepository
                .findAll()
                .stream()
                .filter(demand -> demand.getPub().getPubId() == pubId && demand.getProduct().getProId() == proId).toList();
        if (demandList.isEmpty())
            createNewDemand(pubId, product, demandDTO);
        if (demandList.size() > 0)
            for (Demand demand : demandList) {
                demand.setQuantity(demand.getQuantity() + 1);
                demandRepository.save(demand);
                demandDTO.setName(demand.getProduct().getName());
                demandDTO.setQuantity(demandRepository.findAmountOfQuantity(pubId));
                demandDTO.setPubName(demand.getPub().getPubName());
                demandDTO.setProId(demand.getProduct().getProId());
                demandDTO.setPubId(demand.getPub().getPubId());
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

        demandDTO.setName(product.getName());
        demandDTO.setQuantity(demandRepository.findAmountOfQuantity(pubId));
        demandDTO.setPubName(pub.getPubName());
        demandDTO.setProId(product.getProId());
        demandDTO.setPubId(pub.getPubId());
    }

    public List<DemandDTO> decreaseQuantity(int proId) {
        Pub pub = (Pub) getPub();
        int pubId = pub.getPubId();
        List<DemandDTO> demandDTOList = new ArrayList<>();
        for (Demand demands : demandRepository.findAll()) {
            DemandDTO demandDTO = new DemandDTO();
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                if (demands.getQuantity() >= 1) {
                    demands.setQuantity(demands.getQuantity() - 1);
                    if (demands.getQuantity() >= 1) {
                        demandRepository.save(demands);
                        demandDTO.setQuantity(demandRepository.findAmountOfQuantity(pubId));
                        demandDTO.setName(demands.getProduct().getName());
                        demandDTO.setPubName(demands.getPub().getPubName());
                        demandDTO.setProId(demands.getProduct().getProId());
                        demandDTO.setPubId(demands.getPub().getPubId());
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

    public void deleteDemand(int proId) {
        Pub pub = (Pub) getPub();
        int pubId = pub.getPubId();
        for (Demand demands : demandRepository.findAll()) {
            if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demandRepository.delete(demands);
            }
        }
    }
}
