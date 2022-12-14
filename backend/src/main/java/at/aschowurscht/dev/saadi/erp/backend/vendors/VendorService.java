package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.VendorDTO;
import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VendorService {
    final VendorRepository vendorRepository;

    public VendorDTO createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return new VendorDTO(vendor.getName(), vendor.getAddress(), vendor.getVenId());
    }

    public Vendor getVendorById(int venId) {
        return vendorRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
    }

    public List<ProductDTO> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
        return vendor.getProducts().stream().map(this::convertToProductDTO).collect(Collectors.toList());
    }

    public List<VendorDTO> getAllVendors() {
        return (vendorRepository
                .findAll())
                .stream()
                .map(this::convertToVendorDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToProductDTO(Product product){
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    private VendorDTO convertToVendorDTO(Vendor vendor) {
        return new VendorDTO(vendor.getName(), vendor.getAddress(), vendor.getVenId());
    }


    public VendorDTO updateVendor(Vendor vendor, int venId) {
        Vendor updateVendor = vendorRepository.findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
        updateVendor.setName(vendor.getName());
        updateVendor.setAddress(vendor.getAddress());
        vendorRepository.save(updateVendor);
        return new VendorDTO(vendor.getName(), vendor.getAddress(), venId);
    }

    public void deleteVendor(int venId) {
        vendorRepository.deleteById(venId);
    }
}
