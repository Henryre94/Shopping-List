package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.credentials.Credential;
import at.aschowurscht.dev.saadi.erp.backend.credentials.CredentialRepository;
import at.aschowurscht.dev.saadi.erp.backend.dtos.PubDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.PubNoIdDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PubService {
    final PubRepository pubRepository;
    final CredentialRepository credentialRepository;

    public PubNoIdDTO createPub(PubNoIdDTO pubNoIdDTO) {
        Pub pub = new Pub();
        pub.setPubName(pubNoIdDTO.getPubName());
        pubRepository.save(pub);
        return pubNoIdDTO;
    }

    public PubDTO getPubById(int pubId) {
        Pub pub = pubRepository.findById(pubId)
                .orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        PubDTO pubDto = new PubDTO();
        pubDto.setPubName(pub.getPubName());
        pubDto.setPubId(pub.getPubId());
        return pubDto;
    }

    public List<PubDTO> getAllPubs() {
        return pubRepository
                .findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PubDTO convertToDto(Pub pub) {
        PubDTO pubDto = new PubDTO();
        pubDto.setPubName(pub.getPubName());
        pubDto.setPubId(pub.getPubId());
        return pubDto;
    }

    public PubDTO updatePub(PubDTO pubDto, int pubId) {
        Pub updatePub = pubRepository.findById(pubId)
                .orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        updatePub.setPubName(pubDto.getPubName());
        pubRepository.save(updatePub);
        return pubDto;
    }

    public void deletePub(int pubId) {
        Pub pub = pubRepository.findById(pubId)
                .orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: " + pubId));
        List<Credential> credentials = credentialRepository
                .findAll()
                .stream()
                .filter(credential -> credential.getPub() == pub)
                .toList();
        for (Credential credential : credentials)
            credential.setPub(null);
        pubRepository.deleteById(pubId);
    }
}
