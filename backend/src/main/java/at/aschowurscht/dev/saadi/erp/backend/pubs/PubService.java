package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class PubService {
    @Autowired
    PubCRUDRepository pubCRUDRepository;

    public PubDto createPub(PubDto pubDto) {
        Pub pub = new Pub();
        pub.setName(pubDto.name);
        pubCRUDRepository.save(pub);
        return pubDto;
    }

    public PubDto getPubById(int pubId) {
        Pub pub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        PubDto pubDto = new PubDto();
        pubDto.setName(pub.getName());
        return pubDto;
    }

    public List<PubDto> getAllPubs() {
        return ( (List<Pub>)pubCRUDRepository
                .findAll())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private PubDto convertToDto(Pub pub){
        PubDto pubDto = new PubDto();
        pubDto.setName(pub.getName());
        return pubDto;
    }

    public PubDto updatePub(PubDto pubDto, int pubId) {
        Pub updatePub = pubCRUDRepository.findById(pubId).orElseThrow(() -> new IllegalStateException("Pub ID nicht gefunden: "+pubId));
        updatePub.setName(pubDto.name);
        pubCRUDRepository.save(updatePub);

        return pubDto;
    }

    public void deletePub(int pubId) {
        pubCRUDRepository.deleteById(pubId);
    }
}
