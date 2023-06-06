package schedule.pro.application.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.Clocking;

@Repository
public interface ClockingRepository extends CrudRepository<Clocking, Long> {

    Clocking save(Clocking clocking);
}
