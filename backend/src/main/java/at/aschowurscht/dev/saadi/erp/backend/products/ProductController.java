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
    public void post(@RequestBody Product product, @PathVariable int venId) {
        productService.post(product, venId);
    }

    @PostMapping("/{proId}/pubs/{pubId}")
    public void post(@PathVariable int proId, @PathVariable int pubId) {
        productService.post(proId, pubId);
    }

    @GetMapping("/{proId}")
    public Product getById(@PathVariable int proId) {
        return productService.getById(proId);
    }

    @GetMapping()
    public List<Product> get() {
        return productService.get();
    }

    @PutMapping()
    public void put(@RequestBody Product product) {
        productService.put(product);
    }

    @DeleteMapping("/{proId}")
    public void delete(@PathVariable int proId) {
        productService.delete(proId);
    }

}
