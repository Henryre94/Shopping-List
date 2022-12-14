package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/demands")
@CrossOrigin
@RequiredArgsConstructor
public class DemandController {
    final DemandService demandService;
    final AuthenticationFacade authenticationFacade;
    final CredentialRepository credentialRepository;

    @PostMapping("/{proId}")
    public DemandDTO createDemand(@PathVariable int proId) {
        return demandService.createDemand(proId);
    }

    @PutMapping("/{proId}/pubs/{pubId}")
    public List<DemandDTO> decreaseQuantity(@PathVariable int proId, @PathVariable int pubId) {
        return demandService.decreaseQuantity(proId, pubId);
    }

    @DeleteMapping("/{proId}/pubs/{pubId}")
    public void deleteDemand(@PathVariable int proId,@PathVariable int pubId){
        demandService.deleteDemand(proId,pubId);
    }
}
