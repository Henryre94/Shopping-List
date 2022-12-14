package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.demands.DemandService;
import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.ProductDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.VendorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin
@RequiredArgsConstructor
public class VendorController {
    final
    VendorService vendorService;
    final DemandService demandService;

    @PostMapping()
    public VendorDTO createVendor(@RequestBody Vendor vendor) {
       return vendorService.createVendor(vendor);
    }

    @GetMapping("/{venId}")
    public VendorDTO getVendorById(@PathVariable int venId) {
        return vendorService.getVendorById(venId);
    }

    @GetMapping("/{venId}/products")
    public List<ProductDTO> getAllProductsFromVendor(@PathVariable int venId) {
        return vendorService.getAllProductsFromVendor(venId);
    }
    @GetMapping("/{venId}/demands")
    public List<DemandDTO> getAllDemandsFromVendor(@PathVariable int venId) {
        return demandService.getAllDemandsFromVendor(venId);
    }

    @GetMapping()
    public List<VendorDTO> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @PutMapping("/{venId}")
    public VendorDTO updateVendor(@RequestBody Vendor vendor,@PathVariable int venId) {
       return vendorService.updateVendor(vendor,venId);
    }

    @DeleteMapping("/{venId}")
    public void deleteVendor(@PathVariable int venId) {vendorService.deleteVendor(venId);
    }
}