package schedule.pro.application.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
