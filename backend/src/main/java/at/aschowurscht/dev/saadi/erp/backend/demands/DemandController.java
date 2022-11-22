package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemandController {
    @Autowired
    DemandService demandService;

    @CrossOrigin
    @PutMapping("api/demands/{proId}")
    public void increaseQuantity(@PathVariable int proId){
        demandService.highDemand(proId);
    }

    @CrossOrigin
    @PutMapping("api/demands/{proId}/reduce")
    public void reduceQuantity(@PathVariable int proId){
        demandService.lowDemand(proId);
    }



}
