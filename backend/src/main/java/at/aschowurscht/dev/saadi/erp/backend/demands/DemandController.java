package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class DemandController {
    @Autowired
    DemandService demandService;

    @CrossOrigin
    @PutMapping("api/demands/{proId}/{pubId}")
    public void increaseQuantity(@PathVariable int proId, @PathVariable int pubId){
        demandService.increaseQuantity(proId, pubId);
    }

    @CrossOrigin
    @PutMapping("api/demands/{proId}/{pubId}/reduce")
    public void decreaseQuantity(@PathVariable int proId, @PathVariable int pubId){
        demandService.decreaseQuantity(proId, pubId);
    }

}
