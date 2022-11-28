package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/demands")
@CrossOrigin
public class DemandController {
    @Autowired
    DemandService demandService;

    @PutMapping("/{proId}/{pubId}/+")
    public void increaseQuantity(@PathVariable int proId, @PathVariable int pubId) {
        demandService.increaseQuantity(proId, pubId);
    }

    @PutMapping("/{proId}/{pubId}/-")
    public void decreaseQuantity(@PathVariable int proId, @PathVariable int pubId) {
        demandService.decreaseQuantity(proId, pubId);
    }

    @GetMapping("/vendor/{venId}")
    @ResponseBody
    public List<DemandDto> getAllDemandsFromVendor(@PathVariable int venId) {
        return demandService.getAllDemandsFromVendor(venId);
    }
}
