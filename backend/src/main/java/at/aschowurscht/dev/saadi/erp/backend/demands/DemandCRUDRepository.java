package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandCRUDRepository extends CrudRepository<Demand,DemandID> {


}
