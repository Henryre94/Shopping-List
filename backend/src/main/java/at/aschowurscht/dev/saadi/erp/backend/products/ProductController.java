package at.aschowurscht.dev.saadi.erp.backend.products;

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
    public Product post(@RequestBody Product product, @PathVariable int venId) {
        return productService.createProduct(product, venId);
    }

    @PostMapping("/{proId}/pubs/{pubId}")
    public void post(@PathVariable int proId, @PathVariable int pubId) {
        productService.createDemand(proId, pubId);
    }

    @GetMapping("/{proId}")
    public Product getById(@PathVariable int proId) {
        return productService.getProductById(proId);
    }

    @GetMapping()
    public List<Product> get() {
        return productService.getAllProduct();
    }

    @PutMapping()
    public void put(@RequestBody Product product) {
        productService.updateProduct(product);
    }

    @DeleteMapping("/{proId}")
    public void delete(@PathVariable int proId) {
        productService.deleteProduct(proId);
    }

}
