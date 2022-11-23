package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import at.aschowurscht.dev.saadi.erp.backend.vendors.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pubs")
public class PubController {
    @Autowired
    PubService pubService;
    @CrossOrigin
    @PostMapping()
    public void postProduct(@RequestBody Pub pub){
        pubService.post(pub);
    }
    @CrossOrigin
    @GetMapping("/{pubId}")
    public Pub getById(@PathVariable int pubId){
        return pubService.getById(pubId);
    }
    @CrossOrigin
    @GetMapping()
    public List<Pub> get(){
        List<Pub> pubList = pubService.get();
        return pubList;
    }
    @CrossOrigin
    @PutMapping()
    public void put(@RequestBody Pub pub){
        pubService.put(pub);
    }
    @CrossOrigin
    @DeleteMapping("/{pubId}")
    public void delete(@PathVariable int pubId) {pubService.delete(pubId);}
}
