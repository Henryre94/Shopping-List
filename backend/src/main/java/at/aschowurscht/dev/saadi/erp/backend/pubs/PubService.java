package at.aschowurscht.dev.saadi.erp.backend.pubs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PubService {
    @Autowired
    PubCRUDRepository pubCRUDRepository;
}
