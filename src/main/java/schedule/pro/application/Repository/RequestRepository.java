package schedule.pro.application.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.Request;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    List<Request> findAll();
    Optional<Request> findById(long id);
}
