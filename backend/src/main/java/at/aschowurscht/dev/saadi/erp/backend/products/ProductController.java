package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductNoIdDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    final ProductService productService;

    @PostMapping("/{venId}")
    public ProductNoIdDTO createProduct(@RequestBody ProductNoIdDTO productNoIdDTO, @PathVariable int venId) {
        return productService.createProduct(productNoIdDTO, venId);
    }
    @GetMapping("/{proId}")
    public ProductDTO getProductById(@PathVariable int proId) {
        return productService.getProductById(proId);
    }

    @GetMapping()
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }

    @PutMapping("/{proId}")
    public ProductDTO updateProduct(@RequestBody Product product, @PathVariable int proId) {
      return  productService.updateProduct(product, proId);
    }

    @DeleteMapping("/{proId}")
    public void deleteProduct(@PathVariable int proId) {
        productService.deleteProduct(proId);
    }


}
