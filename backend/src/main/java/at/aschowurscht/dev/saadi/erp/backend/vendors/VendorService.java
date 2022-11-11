package at.aschowurscht.dev.saadi.erp.backend.vendors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {
    @Autowired
    VendorCRUDRepository vendorCRUDRepository;
}
