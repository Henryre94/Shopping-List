package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductNoIdDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductCRUDRepository productCRUDRepository;
    final VendorCRUDRepository vendorCRUDRepository;
    final PubCRUDRepository pubCRUDRepository;
    final DemandCRUDRepository demandCRUDRepository;

    //Create a product and bind it to a vendor
    public ProductNoIdDTO createProduct(ProductNoIdDTO productNoIdDTO, int venId) {
        Product product = new Product();
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(RuntimeException::new);
        product.setName(productNoIdDTO.getName());
        product.setUnit(productNoIdDTO.getUnit());
        product.setVendor(vendor);
        productCRUDRepository.save(product);
        return productNoIdDTO;
    }

    //Create a demand from a product and bind it to a Pub, if the demand for that product already exist is the quantity increase by 1
    public DemandDTO createDemand(int proId, int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        DemandDTO demandDTO = new DemandDTO();
        List<Demand> demandList = demandCRUDRepository.findAll();
        Demand demand = new Demand();
        if (demandList.isEmpty()) {
            //Extracted method to create a new demand
            createNewDemand(pub, product, demandDTO, demand);
        }if (demandList.size() > 0)
            createDemandDTOFromDemandList(proId, pubId, pub, product, demandDTO, demand);//credit to Filipp
        return demandDTO;
    }

    private void createDemandDTOFromDemandList(int proId, int pubId, Pub pub, Product product, DemandDTO demandDTO, Demand demand) {
        for (Demand demands : demandCRUDRepository.findAll()) {
            if (demands.getProduct().getProId() != proId || demands.getPub().getPubId() != pubId) {
                //Extracted method to create a new demand
                createNewDemand(pub, product, demandDTO, demand);
            } else if (demands.getProduct().getProId() == proId && demands.getPub().getPubId() == pubId) {
                demands.setQuantity(demands.getQuantity() + 1);
                demandCRUDRepository.save(demands);
                demandDTO.setName(demands.getProduct().getName());
                demandDTO.setQuantity(demands.getQuantity());
                demandDTO.setPubName(demands.getPub().getPubName());
                demandDTO.setProId(demands.getProduct().getProId());
            }
        }
    }

    private void createNewDemand(Pub pub, Product product, DemandDTO demandDTO, Demand demand) {
        //A new demand for the product is created
        product.newDemand(demand);
        //A product is assign to the demand
        demand.setProduct(product);
        //A new demand for the pub is created
        pub.newDemand(demand);
        //A pub is assign to the demand
        demand.setPub(pub);
        //The quantity of the product is established to 1
        demand.setQuantity(1);
        demandCRUDRepository.save(demand);
        pubCRUDRepository.save(pub);
        productCRUDRepository.save(product);
        //The DTO that is returned to the Frontend with the values of the new demand
        demandDTO.setName(product.getName());
        demandDTO.setQuantity(demand.getQuantity());
        demandDTO.setPubName(pub.getPubName());
        demandDTO.setProId(product.getProId());
    }

    public ProductDTO getProductById(int proId) {
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public List<ProductDTO> getAllProduct() {
        return (productCRUDRepository
                .findAll())
                .stream()//Get all the Elements as single objects from the List (findAll)
                .map(this::convertToDto)//Apply the method convertToDto to each of the objects
                .collect(Collectors.toList());//Collect the List with the applied method
    }

    //Convert the objects into a DTO to return it to the frontend
    private ProductDTO convertToDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public ProductDTO updateProduct(Product product, int proId) {
        Product updateProduct = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        updateProduct.setName(product.getName());
        updateProduct.setUnit(product.getUnit());
        productCRUDRepository.save(updateProduct);
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(updateProduct.getProId());
        return productDto;
    }

    public void deleteProduct(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
