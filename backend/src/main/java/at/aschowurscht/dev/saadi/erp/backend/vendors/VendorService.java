package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.dtos.VendorDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.products.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class VendorService {
    final VendorRepository vendorRepository;

    final ProductRepository productRepository;

    public void createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
    }

    public Vendor getVendorById(int venId) {
        Vendor vendor = vendorRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));

        return vendor;
    }

    public List<Product> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));

        return vendor.getProducts();
    }

    public List<Vendor> getAllVendors() {
        return ((List<Vendor>) vendorRepository.findAll());
    }

    public VendorDTO updateVendor(Vendor vendor, int venId) {
        Vendor updateVendor = vendorRepository.findById(venId).orElseThrow(()->new IllegalStateException("Vendor ID nicht gefunden: "+venId));
        updateVendor.setName(vendor.getName());
        updateVendor.setAddress(vendor.getAddress());
        vendorRepository.save(updateVendor);
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setAddress(vendor.getAddress());
        vendorDTO.setVenId(venId);
        return vendorDTO;
    }

    public void deleteVendor(int venId) {
        vendorRepository.deleteById(venId);
    }
}
