package at.aschowurscht.dev.saadi.erp.backend.demands;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface DemandRepository extends JpaRepository<Demand,DemandID> {

    @Query("select sum(d.quantity) from Demand d where d.pub.pubId=?1")
    int findAmountOfQuantity(Integer pubId);




}
