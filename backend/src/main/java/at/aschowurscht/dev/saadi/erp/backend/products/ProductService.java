package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final VendorRepository vendorRepository;

    public ProductDTO createProduct(Product product, int venId) {
        Vendor vendor = vendorRepository.findById(venId).orElseThrow(RuntimeException::new);
        product.setVendor(vendor);
        productRepository.save(product);
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    public ProductDTO getProductById(int proId) {
        Product product = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    public List<ProductDTO> getAllProduct() {
        return (productRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    public ProductDTO updateProduct(Product product, int proId) {
        Product updateProduct = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        updateProduct.setName(product.getName());
        updateProduct.setUnit(product.getUnit());
        productRepository.save(updateProduct);
        return new ProductDTO(product.getName(), product.getUnit(), product.getProId());
    }

    public void deleteProduct(int proId) {
        productRepository.deleteById(proId);
    }

}
