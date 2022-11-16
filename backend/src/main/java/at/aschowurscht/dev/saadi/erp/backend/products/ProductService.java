package at.aschowurscht.dev.saadi.erp.backend.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductCRUDRepository productCRUDRepository;

    public void post(Product product){
        productCRUDRepository.save(product);
    }
    public Product getById(int proId){
        Product product = productCRUDRepository.findById(proId).get();
        return product;
    }
    public List<Product> get(){
        return ((List<Product>) productCRUDRepository.findAll());
    }
    public void put(int proId){
        productCRUDRepository.findById(proId);
    }
    public void delete(int proId){
        productCRUDRepository.deleteById(proId);
    }

}
