package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandRepository extends JpaRepository<Demand,DemandID> {
}
