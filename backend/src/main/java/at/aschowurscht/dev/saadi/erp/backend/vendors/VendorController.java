package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    VendorService vendorService;
    @CrossOrigin
    @PostMapping()
    public void postProduct(@RequestBody Vendor vendor){
        vendorService.post(vendor);
    }
    @CrossOrigin
    @GetMapping("/{venId}")
    public Vendor getById(@PathVariable int venId){
        return vendorService.getById(venId);
    }
    @CrossOrigin
    @GetMapping("/{venId}/products")
    public List<Product> productsFromVendor(@PathVariable int venId){
        List<Product> productList = vendorService.productsFromVendor(venId);
        return productList;
    }
    @CrossOrigin
    @GetMapping()
    public List<Vendor> get(){
        List<Vendor> pubList = vendorService.get();
        return pubList;
    }
    @CrossOrigin
    @PutMapping()
    public void put(@RequestBody Vendor vendor){
        vendorService.put(vendor);
    }
    @CrossOrigin
    @DeleteMapping("/{venId}")
    public void delete(@PathVariable int venId) {vendorService.delete(venId);}
}