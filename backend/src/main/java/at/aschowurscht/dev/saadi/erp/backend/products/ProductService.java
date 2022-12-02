package at.aschowurscht.dev.saadi.erp.backend.products;


import at.aschowurscht.dev.saadi.erp.backend.demands.DemandCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductNoIdDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.PubCRUDRepository;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import at.aschowurscht.dev.saadi.erp.backend.vendors.VendorCRUDRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductCRUDRepository productCRUDRepository;
    final VendorCRUDRepository vendorCRUDRepository;
    final PubCRUDRepository pubCRUDRepository;
    final DemandCRUDRepository demandCRUDRepository;

    public ProductNoIdDTO createProduct(ProductNoIdDTO productNoIdDTO, int venId) {
        Product product = new Product();
        Vendor vendor = vendorCRUDRepository.findById(venId).orElseThrow(RuntimeException::new);
        product.setName(productNoIdDTO.getName());
        product.setUnit(productNoIdDTO.getUnit());
        product.setVendor(vendor);
        productCRUDRepository.save(product);
        return productNoIdDTO;
    }

    public ProductDTO getProductById(int proId) {
        Product product = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(product.getProId());
        return productDto;
    }

    public List<ProductDTO> getAllProduct() {
        return (productCRUDRepository
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
        Product updateProduct = productCRUDRepository.findById(proId).orElseThrow(() -> new IllegalStateException("Produkt ID nicht gefunden: " + proId));
        ProductDTO productDto = new ProductDTO();
        updateProduct.setName(product.getName());
        updateProduct.setUnit(product.getUnit());
        productCRUDRepository.save(updateProduct);
        productDto.setName(product.getName());
        productDto.setUnit(product.getUnit());
        productDto.setProId(updateProduct.getProId());
        return productDto;
    }

    public void deleteProduct(int proId) {
        productCRUDRepository.deleteById(proId);
    }

}
