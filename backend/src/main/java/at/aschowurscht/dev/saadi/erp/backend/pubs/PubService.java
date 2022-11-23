package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PubService {
    @Autowired
    PubCRUDRepository pubCRUDRepository;

    public void createPub(Pub pub) {
        pubCRUDRepository.save(pub);
    }

    public Pub getPubById(int pubId) {
        Optional<Pub> pub = pubCRUDRepository.findById(pubId);
        if (pub.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pub.get();
    }

    public List<Pub> getAllPubs() {
        return ((List<Pub>) pubCRUDRepository.findAll());
    }

    public void updatePub(Pub pub) {
        pubCRUDRepository.save(pub);
    }

    public void deletePub(int pubId) {
        pubCRUDRepository.deleteById(pubId);
    }
}
