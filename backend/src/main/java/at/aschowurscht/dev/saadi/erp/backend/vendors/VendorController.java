package at.aschowurscht.dev.saadi.erp.backend.vendors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {
    @Autowired
    VendorService vendorService;
}