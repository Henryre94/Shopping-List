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
    public ProductDto post(@RequestBody ProductDto productDto, @PathVariable int venId) {
        return productService.createProduct(productDto, venId);
    }

    @PostMapping("/{proId}/pubs/{pubId}")
    public DemandDto post(@PathVariable int proId, @PathVariable int pubId) {
        return productService.createDemand(proId, pubId);
    }

    @GetMapping("/{proId}")
    public ProductDto getById(@PathVariable int proId) {
        return productService.getProductById(proId);
    }

    @GetMapping()
    public List<ProductDto> get() {
        return productService.getAllProduct();
    }

    @PutMapping("/{proId}")
    public ProductDto put(@RequestBody ProductDto productDto,@PathVariable int proId) {
      return  productService.updateProduct(productDto, proId);
    }

    @DeleteMapping("/{proId}")
    public void delete(@PathVariable int proId) {
        productService.deleteProduct(proId);
    }


}
