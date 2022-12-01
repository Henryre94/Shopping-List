package at.aschowurscht.dev.saadi.erp.backend.pubs;

import at.aschowurscht.dev.saadi.erp.backend.dtos.PubDTO;
import at.aschowurscht.dev.saadi.erp.backend.dtos.PubNoIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PubService {
    @Autowired
    PubCRUDRepository pubCRUDRepository;

    public PubNoIdDTO createPub(PubNoIdDTO pubNoIdDTO) {
        Pub pub = new Pub();
        pub.setPubName(pubNoIdDTO.getName());
        pubCRUDRepository.save(pub);
        return pubNoIdDTO;
    }

    public PubDTO getPubById(int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        PubDTO pubDto = new PubDTO();
        pubDto.setName(pub.getPubName());
        pubDto.setPubId(pub.getPubId());
        return pubDto;
    }

    public List<PubDTO> getAllPubs() {
        return ( (List<Pub>)pubCRUDRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private PubDTO convertToDto(Pub pub){
        PubDTO pubDto = new PubDTO();
        pubDto.setName(pub.getPubName());
        pubDto.setPubId(pub.getPubId());
        return pubDto;
    }

    public PubDTO updatePub(PubDTO pubDto, int pubId) {
        Pub updatePub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        updatePub.setPubName(pubDto.getName());
        pubCRUDRepository.save(updatePub);
        return pubDto;
    }

    public void deletePub(int pubId) {
        pubCRUDRepository.deleteById(pubId);
    }
}
