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
    public PubNoIdDTO createPub(@RequestBody PubNoIdDTO pubNoIdDTO) {
       return pubService.createPub(pubNoIdDTO);
    }

    @GetMapping("/{pubId}")
    public PubDTO getPubById(@PathVariable int pubId) {
        return pubService.getPubById(pubId);
    }

    @GetMapping()
    public List<PubDTO> getAllPubs() {
        return pubService.getAllPubs();

    }
    @PutMapping("/{pubId}")
    public PubDTO updatePub(@RequestBody PubDTO pubDto, @PathVariable int pubId) {
        return pubService.updatePub(pubDto, pubId);
    }

    @DeleteMapping("/{pubId}")
    public void deletePub(@PathVariable int pubId) {
        pubService.deletePub(pubId);
    }
}
