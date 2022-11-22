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
    public void post(Pub pub){
        pubCRUDRepository.save(pub);
    }
    public Pub getById(int pubId){
        Optional<Pub> pub = pubCRUDRepository.findById(pubId);
        if (pub.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return pub.get();
    }
    public List<Pub> get(){
        return ((List<Pub>) pubCRUDRepository.findAll());
    }
    public void put(Pub pub){
        pubCRUDRepository.save(pub);
    }
    public void delete(int pubId){
        pubCRUDRepository.deleteById(pubId);
    }
}
