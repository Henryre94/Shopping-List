package at.aschowurscht.dev.saadi.erp.backend.products;

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

    public void post(Product product, int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        product.setVendor(vendor);
        productCRUDRepository.save(product);
    }

    public Product getById(int proId) {
        Product product = productCRUDRepository.findById(proId).get();
        return product;
    }

    public List<Product> get() {
        return ((List<Product>) productCRUDRepository.findAll());
    }

    public void put(Product product) {
        productCRUDRepository.save(product);
    }

    public void delete(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
