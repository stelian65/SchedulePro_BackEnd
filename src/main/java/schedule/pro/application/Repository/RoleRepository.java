package schedule.pro.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.pro.application.Entity.ERole;
import schedule.pro.application.Entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}