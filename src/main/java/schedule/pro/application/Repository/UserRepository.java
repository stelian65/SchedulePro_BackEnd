package schedule.pro.application.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstnameAndLastname(String firstname, String Lastname);
    User save(User user);
    void deleteById(Integer id);
}
