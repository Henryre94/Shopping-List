package at.aschowurscht.dev.saadi.erp.backend.demands;

import at.aschowurscht.dev.saadi.erp.backend.credentials.Credential;
import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.DemandDTO;
import at.aschowurscht.dev.saadi.erp.backend.pubs.Pub;
import at.aschowurscht.dev.saadi.erp.backend.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DemandController {
    final DemandService demandService;
    final AuthenticationFacade authenticationFacade;
    final CredentialRepository credentialRepository;

    public Pub getPub() {
        Credential credential = credentialRepository.findByUsername(authenticationFacade.getAuthentication().getName());
        return credential.getPub();
    }

    @PostMapping(value = {"api/demands/{proId}", "api/demands/{proId}/{pubId}"})
    public DemandDTO createDemand(@PathVariable int proId, @PathVariable(required = false) Integer pubId) {
        return demandService.createDemand(proId, checkAccount(pubId));
    }

    @PutMapping(value = {"api/demands/{proId}", "api/demands/{proId}/{pubId}"})
    public List<DemandDTO> decreaseQuantity(@PathVariable int proId, @PathVariable(required = false) Integer pubId) {
        return demandService.decreaseQuantity(proId, checkAccount(pubId));
    }

    @DeleteMapping(value = {"api/demands/{proId}", "api/demands/{proId}/{pubId}"})
    public void deleteDemand(@PathVariable int proId, @PathVariable(required = false) Integer pubId) {
        demandService.deleteDemand(proId, checkAccount(pubId));
    }

    private Integer checkAccount(Integer pubId) {
        if (pubId == null) {
            Pub pub = getPub();
            pubId = pub.getPubId();
        }
        return pubId;
    }
}
