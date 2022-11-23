package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public Product createProduct(Product product, int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        product.setVendor(vendor);
        productCRUDRepository.save(product);
        return product;
    }

    public void createDemand(int proId, int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).get();
        Product product = productCRUDRepository.findById(proId).get();
        Demand demand = new Demand();

        product.newDemand(demand);
        demand.setProduct(product);

        pub.newDemand(demand);
        demand.setPub(pub);
        demand.setQuantity(1);

        demandCRUDRepository.save(demand);
        pubCRUDRepository.save(pub);
        productCRUDRepository.save(product);
    }

    public Product getProductById(int proId) {
        Optional<Product> product = productCRUDRepository.findById(proId);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return product.get();
    }

    public List<Product> getAllProduct() {
        return productCRUDRepository.findAll();
    }

    public void updateProduct(Product product) {
        productCRUDRepository.save(product);
    }

    public void deleteProduct(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
