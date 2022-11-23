package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.Demand;
import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

    public void createProduct(Product product, int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        product.setVendor(vendor);
        productCRUDRepository.save(product);
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
        Product product = productCRUDRepository.findById(proId).get();
        return product;
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
