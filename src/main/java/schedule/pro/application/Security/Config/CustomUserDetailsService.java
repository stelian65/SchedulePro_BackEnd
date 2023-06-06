package schedule.pro.application.Security.Config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.CustomUserDetails;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    UserRepository userRepository;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        CustomUserDetails userDetails = new CustomUserDetails(user.getPassword(),user.getEmail(),user.getRole());
        return userDetails;
    }
}
