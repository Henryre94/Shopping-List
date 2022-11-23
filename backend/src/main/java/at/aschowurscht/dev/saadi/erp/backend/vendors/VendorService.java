package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    VendorCRUDRepository vendorCRUDRepository;
    @Autowired
    ProductCRUDRepository productCRUDRepository;

    public void createVendor(Vendor vendor) {
        vendorCRUDRepository.save(vendor);
    }

    public Vendor getVendorById(int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        return vendor;
    }

    public List<Product> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        return vendor.getProductsList();
    }

    public List<Vendor> getAllVendors() {
        return ((List<Vendor>) vendorCRUDRepository.findAll());
    }

    public void updateVendor(Vendor vendor) {
        vendorCRUDRepository.save(vendor);
    }

    public void deleteVendor(int venId) {
        vendorCRUDRepository.deleteById(venId);
    }
}
