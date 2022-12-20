package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.dtos.PubDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.PubNoIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pubs")
@CrossOrigin
@RequiredArgsConstructor
public class PubController {
    final PubService pubService;

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