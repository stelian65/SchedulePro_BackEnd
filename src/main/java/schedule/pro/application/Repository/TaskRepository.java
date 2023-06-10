package schedule.pro.application.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

    Optional<Task> findByTitle(String name);
    List<Task> findByUserEmail(String username);
    List<Task> findAll();
    Optional<Task> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByTitle(String title);


}
