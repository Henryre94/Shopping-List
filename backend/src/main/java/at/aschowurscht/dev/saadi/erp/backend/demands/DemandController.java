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

    @PutMapping("/{proId}")
    public List<DemandDTO> decreaseQuantity(@PathVariable int proId) {
        return demandService.decreaseQuantity(proId);
    }

    @DeleteMapping("/{proId}")
    public void deleteDemand(@PathVariable int proId){
        demandService.deleteDemand(proId);
    }
}
