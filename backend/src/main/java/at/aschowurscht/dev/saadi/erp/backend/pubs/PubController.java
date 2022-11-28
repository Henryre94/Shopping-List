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
    public PubDto createPub(@RequestBody PubDto pubDto) {
       return pubService.createPub(pubDto);
    }

    @GetMapping("/{pubId}")
    public PubDto getPubById(@PathVariable int pubId) {
        return pubService.getPubById(pubId);
    }

    @GetMapping()
    public List<PubDto> getAllPubs() {
        return pubService.getAllPubs();

    }
    @PutMapping("/{pubId}")
    public PubDto updatePub(@RequestBody PubDto pubDto,@PathVariable int pubId) {
        return pubService.updatePub(pubDto, pubId);
    }

    @DeleteMapping("/{pubId}")
    public void deletePub(@PathVariable int pubId) {
        pubService.deletePub(pubId);
    }
}
