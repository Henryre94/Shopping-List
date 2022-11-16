package at.aschowurscht.dev.saadi.erp.backend.vendors;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {
    @Autowired
    VendorCRUDRepository vendorCRUDRepository;


    public void post(Vendor vendor){
        vendorCRUDRepository.save(vendor);
    }
    public Vendor getById(int venId){
        Vendor vendor = vendorCRUDRepository.findById(venId).get();
        return vendor;
    }
    public List<Vendor> get(){
        return ((List<Vendor>) vendorCRUDRepository.findAll());
    }
    public void put(int venId){
        vendorCRUDRepository.findById(venId);
    }
    public void delete(int venId){
        vendorCRUDRepository.deleteById(venId);
    }
}
