package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.demands.DemandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/{venId}")
    public ProductDto createProduct(@RequestBody ProductDto productDto, @PathVariable int venId) {
        return productService.createProduct(productDto, venId);
    }

    @PostMapping("/{proId}/pubs/{pubId}")
    public DemandDto createDemand(@PathVariable int proId, @PathVariable int pubId) {
        return productService.createDemand(proId, pubId);
    }

    @GetMapping("/{proId}")
    public ProductDto getProductById(@PathVariable int proId) {
        return productService.getProductById(proId);
    }

    @GetMapping()
    public List<ProductDto> getAllProduct() {
        return productService.getAllProduct();
    }

    @PutMapping("/{proId}")
    public ProductDto updateProduct(@RequestBody ProductDto productDto,@PathVariable int proId) {
      return  productService.updateProduct(productDto, proId);
    }

    @DeleteMapping("/{proId}")
    public void deleteProduct(@PathVariable int proId) {
        productService.deleteProduct(proId);
    }


}
