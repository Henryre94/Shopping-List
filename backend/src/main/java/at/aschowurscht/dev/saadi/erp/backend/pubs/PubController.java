package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pubs")
@CrossOrigin
public class PubController {
    @Autowired
    PubService pubService;

    @PostMapping()
    public void postProduct(@RequestBody Pub pub) {
        pubService.createPub(pub);
    }

    @GetMapping("/{pubId}")
    public Pub getById(@PathVariable int pubId) {
        return pubService.getPubById(pubId);
    }

    @GetMapping()
    public List<Pub> get() {
        return pubService.getAllPubs();

    }

    @PutMapping()
    public void put(@RequestBody Pub pub) {
        pubService.updatePub(pub);
    }

    @DeleteMapping("/{pubId}")
    public void delete(@PathVariable int pubId) {
        pubService.deletePub(pubId);
    }
}
