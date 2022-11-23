package at.aschowurscht.dev.saadi.erp.backend.demands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@RestController
@RequestMapping("api/demands")
public class DemandController {
    @Autowired
    DemandService demandService;

    @CrossOrigin
    @PutMapping("/{proId}/{pubId}")
    public void increaseQuantity(@PathVariable int proId, @PathVariable int pubId){
        demandService.increaseQuantity(proId, pubId);
    }

    @CrossOrigin
    @PutMapping("/{proId}/{pubId}/reduce")
    public void decreaseQuantity(@PathVariable int proId, @PathVariable int pubId){
        demandService.decreaseQuantity(proId, pubId);
    }
    @CrossOrigin
    @GetMapping("/vendor/{venId}")
    @ResponseBody
    public Set<DemandDto> getDemandsFromVendor(@PathVariable int venId){
        return demandService.getAllDemandsFromVendor(venId);
    }
}
