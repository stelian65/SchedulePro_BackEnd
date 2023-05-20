package schedule.pro.application.Service;

import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;


public interface  UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
    User assignTask(String name,String username);

}
