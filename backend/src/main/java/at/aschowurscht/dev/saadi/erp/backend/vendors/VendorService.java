package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
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

    public VendorDTO createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return new VendorDTO(vendor.getName(), vendor.getAddress(), vendor.getVenId());
    }

    public VendorDTO getVendorById(int venId) {
      Vendor vendor =  vendorRepository.findById(venId)
              .orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
      return  new VendorDTO(vendor.getName(), vendor.getAddress(), venId);
    }

    public List<ProductDTO> getAllProductsFromVendor(int venId) {
        Vendor vendor = vendorRepository.findById(venId)
                .orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
        return vendor.getProducts()
                .stream()
                .map(this::convertToProductDTO)
                .toList();
    }

    public List<VendorDTO> getAllVendors() {
        return (vendorRepository
                .findAll())
                .stream()
                .map(this::convertToVendorDTO)
                .toList();
    }

    private ProductDTO convertToProductDTO(Product product){
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    private VendorDTO convertToVendorDTO(Vendor vendor) {
        return new VendorDTO(vendor.getName(), vendor.getAddress(), vendor.getVenId());
    }

    public VendorDTO updateVendor(Vendor vendor, int venId) {
        Vendor updateVendor = vendorRepository
                .findById(venId).orElseThrow(() -> new IllegalStateException("Vendor ID nicht gefunden: " + venId));
        updateVendor.setName(vendor.getName());
        updateVendor.setAddress(vendor.getAddress());
        vendorRepository.save(updateVendor);
        return new VendorDTO(vendor.getName(), vendor.getAddress(), venId);

    }

    public void deleteVendor(int venId) {
        List<Product> products = productRepository
                .findAll()
                .stream()
                .filter(product -> product.getVendor().getVenId() == venId).toList();
        productRepository.deleteAll(products);
        vendorRepository.deleteById(venId);
    }
}
