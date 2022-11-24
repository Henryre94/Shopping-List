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
    public PubDto postProduct(@RequestBody PubDto pubDto) {
       return pubService.createPub(pubDto);
    }

    @GetMapping("/{pubId}")
    public PubDto getById(@PathVariable int pubId) {
        return pubService.getPubById(pubId);
    }

    @GetMapping()
    public List<PubDto> get() {
        return pubService.getAllPubs();

    }
    @PutMapping("/{pubId}")
    public PubDto put(@RequestBody PubDto pubDto,@PathVariable int pubId) {
        return pubService.updatePub(pubDto, pubId);
    }

    @DeleteMapping("/{pubId}")
    public void delete(@PathVariable int pubId) {
        pubService.deletePub(pubId);
    }
}
