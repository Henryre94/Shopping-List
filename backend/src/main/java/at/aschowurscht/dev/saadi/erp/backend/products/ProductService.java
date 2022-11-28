package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandDto;
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
    public ProductDto createProduct(ProductDto productDto, int venId) {
        Product product = new Product();
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(RuntimeException::new);
        product.setName(productDto.name);
        product.setUnit(productDto.unit);
        product.setVendor(vendor);
        productCRUDRepository.save(product);
        return productDto;
    }
    //Create a demand from a product and bind it to a Pub
    public DemandDto createDemand(int proId, int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        Demand demand = new Demand();
        DemandDto demandDto = new DemandDto();

        product.newDemand(demand);
        demand.setProduct(product);

        pub.newDemand(demand);
        demand.setPub(pub);
        demand.setQuantity(1);

        demandCRUDRepository.save(demand);
        pubCRUDRepository.save(pub);
        productCRUDRepository.save(product);

        demandDto.setName(product.getName());
        demandDto.setQuantity(demand.getQuantity());

        return demandDto;
    }
    public ProductDto getProductById(int proId) {
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        return productDto;
    }
    public List<ProductDto> getAllProduct() {
        return ( productCRUDRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        return productDto;
    }

    public ProductDto updateProduct(ProductDto productDto, int proId) {
        Product updateProduct = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: "+proId));
        updateProduct.setName(productDto.name);
        updateProduct.setUnit(productDto.unit);
        productCRUDRepository.save(updateProduct);

        return productDto;
    }

    public void deleteProduct(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
