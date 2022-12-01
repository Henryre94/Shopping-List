package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class VendorService {
    final VendorCRUDRepository vendorCRUDRepository;
    final ProductCRUDRepository productCRUDRepository;

    public void createVendor(Vendor vendor) {
        vendorCRUDRepository.save(vendor);
    }

    public Vendor getVendorById(int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: "+venId));

        return vendor;
    }

    public List<Product> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: "+venId));

        return vendor.getProducts();
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
