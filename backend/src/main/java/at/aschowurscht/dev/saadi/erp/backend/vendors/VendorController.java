package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {
    @Autowired
    VendorService vendorService;
    @CrossOrigin
    @PostMapping("/api/vendors")
    public void postProduct(@RequestBody Vendor vendor){
        vendorService.post(vendor);
    }
    @CrossOrigin
    @GetMapping("/api/vendors/{venId}")
    public Vendor getById(@PathVariable int venId){
        return vendorService.getById(venId);
    }
    @CrossOrigin
    @GetMapping("/api/vendors")
    public List<Vendor> get(){
        List<Vendor> pubList = vendorService.get();
        return pubList;
    }
    @CrossOrigin
    @PutMapping("/api/vendors")
    public void put(@RequestBody Vendor vendor){
        vendorService.put(vendor);
    }
    @CrossOrigin
    @DeleteMapping("api/vendors/{venId}")
    public void delete(@PathVariable int venId) {vendorService.delete(venId);}
}