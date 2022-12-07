package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.dtos.VendorDTO;
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
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));

        return vendor;
    }

    public List<Product> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));

        return vendor.getProducts();
    }

    public List<Vendor> getAllVendors() {
        return ((List<Vendor>) vendorCRUDRepository.findAll());
    }

    public VendorDTO updateVendor(Vendor vendor, int venId) {
        Vendor updateVendor = vendorCRUDRepository.findById(venId).orElseThrow(()->new IllegalStateException("Vendor ID nicht gefunden: "+venId));
        updateVendor.setName(vendor.getName());
        updateVendor.setAddress(vendor.getAddress());
        vendorCRUDRepository.save(updateVendor);
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(vendor.getName());
        vendorDTO.setAddress(vendor.getAddress());
        vendorDTO.setVenId(venId);
        return vendorDTO;
    }

    public void deleteVendor(int venId) {
        vendorCRUDRepository.deleteById(venId);
    }
}
