package schedule.pro.application.Security.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import schedule.pro.application.Entity.Role;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Repository.UserRepository;

@Component
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        if (!userRepository.existsByEmail("admin@schedule.pro.com")) {
            User admin = User.builder()
                    .firstname("Vasile")
                    .lastname("Andrei-Stelian")
                    .experience("2 years Ceo at Schedule Pro ")
                    .studies("Bachelor degree at the University Politehnica Timisoara")
                    .phoneNumber("0761876129")
                    .role(Role.ADMIN)
                    .email("admin@schedule.pro.com")
                    .password(passwordEncoder.encode("secret_password123"))
                    .build();
            userRepository.save(admin);
        }
    }
}
