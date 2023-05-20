package schedule.pro.application.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
    Task findByUserUsername(String username);
    Task findByName(String name);

}
