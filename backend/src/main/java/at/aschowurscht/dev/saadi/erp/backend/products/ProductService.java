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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {
    @Autowired
    ProductCRUDRepository productCRUDRepository;
    @Autowired
    VendorCRUDRepository vendorCRUDRepository;
    @Autowired
    PubCRUDRepository pubCRUDRepository;
    @Autowired
    DemandCRUDRepository demandCRUDRepository;

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
    //Create a demand from a product and bind it to a Pub
    public DemandDTO createDemand(int proId, int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        Demand demand = new Demand();
        DemandDTO demandDto = new DemandDTO();

        product.newDemand(demand);//A new demand for the product is created
        demand.setProduct(product);//A product is assign to the demand

        pub.newDemand(demand);//A new demand for the pub is created
        demand.setPub(pub);//A pub is assign to the demand
        demand.setQuantity(1);//The quantity of the product is established to 1

        demandCRUDRepository.save(demand);
        pubCRUDRepository.save(pub);
        productCRUDRepository.save(product);

        //The DTO that is returned to the Frontend with the values of the new demand
        demandDto.setName(product.getName());
        demandDto.setQuantity(demand.getQuantity());
        demandDto.setPubName(pub.getPubName());

        return demandDto;
    }
    public ProductDTO getProductById(int proId) {
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }
    public List<ProductDTO> getAllProduct() {
        return ( productCRUDRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ProductDTO convertToDto(Product product){
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public ProductDTO updateProduct(ProductDTO productDto, int proId) {
        Product updateProduct = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        updateProduct.setName(productDto.getName());
        updateProduct.setUnit(productDto.getUnit());
        productCRUDRepository.save(updateProduct);

        return productDto;
    }

    public void deleteProduct(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
