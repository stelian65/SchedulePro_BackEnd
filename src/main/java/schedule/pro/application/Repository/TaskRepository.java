package schedule.pro.application.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

    Optional<Task> findByName(String name);
    List<Task> findByUserUsername(String username);

}
