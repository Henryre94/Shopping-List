package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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
        Optional<Vendor> vendor = vendorCRUDRepository.findById(venId);
        if (vendor.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return vendor.get();
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
