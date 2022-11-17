package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemandController {
    @Autowired
    DemandService demandService;

    @CrossOrigin
    @PostMapping("/api/demands/{proId}")
    public void post(@RequestBody Demand demand, @PathVariable int proId){
        demandService.post(demand, proId);
    }

}
