package at.aschowurscht.dev.saadi.erp.backend.products;

import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductNoIdDTO;
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
    public ProductNoIdDTO createProduct(@RequestBody ProductNoIdDTO productNoIdDTO, @PathVariable int venId) {
        return productService.createProduct(productNoIdDTO, venId);
    }

    @PostMapping("/{proId}/pubs/{pubId}")
    public DemandDTO createDemand(@PathVariable int proId, @PathVariable int pubId) {
        return productService.createDemand(proId, pubId);
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
    public ProductDTO updateProduct(@RequestBody ProductDTO productDto, @PathVariable int proId) {
      return  productService.updateProduct(productDto, proId);
    }

    @DeleteMapping("/{proId}")
    public void deleteProduct(@PathVariable int proId) {
        productService.deleteProduct(proId);
    }


}
