package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandCRUDRepository extends CrudRepository<Demand,DemandID> {
}
