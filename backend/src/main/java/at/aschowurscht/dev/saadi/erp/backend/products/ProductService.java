package at.aschowurscht.dev.saadi.erp.backend.products;


import at.aschowurscht.dev.saadi.erp.backend.demands.DemandRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductNoIdDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubRepository;
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
    final PubRepository pubRepository;
    final DemandRepository demandRepository;

    public ProductNoIdDTO createProduct(ProductNoIdDTO productNoIdDTO, int venId) {
        Product product = new Product();
        Vendor vendor = vendorRepository.findById(venId).orElseThrow(RuntimeException::new);
        product.setName(productNoIdDTO.getName());
        product.setUnit(productNoIdDTO.getUnit());
        product.setVendor(vendor);
        productRepository.save(product);
        return productNoIdDTO;
    }

    public ProductDTO getProductById(int proId) {
        Product product = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public List<ProductDTO> getAllProduct() {
        return (productRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public ProductDTO updateProduct(Product product, int proId) {
        Product updateProduct = productRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        updateProduct.setName(product.getName());
        updateProduct.setUnit(product.getUnit());
        productRepository.save(updateProduct);
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(updateProduct.getProId());
        return productDto;
    }

    public void deleteProduct(int proId) {
        productRepository.deleteById(proId);
    }

}
