package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PubController {
    @Autowired
    PubService pubService;
    @CrossOrigin
    @PostMapping("/api/pubs")
    public void postProduct(@RequestBody Pub pub){
        pubService.post(pub);
    }
    @CrossOrigin
    @GetMapping("/api/pubs/{pubId}")
    public Pub getById(@PathVariable int pubId){
        return pubService.getById(pubId);
    }
    @CrossOrigin
    @GetMapping("/api/pubs")
    public List<Pub> get(){
        List<Pub> pubList = pubService.get();
        return pubList;
    }
    @CrossOrigin
    @PutMapping("/api/pubs/{pubId}")
    public void put(@PathVariable int pubId){
        pubService.put(pubId);
    }
    @CrossOrigin
    @DeleteMapping("/pubs/{pubId}")
    public void delete(@PathVariable int pubId) {pubService.delete(pubId);}
}
