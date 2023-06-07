package schedule.pro.application.Service;

import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;


public interface  UserService {
    User saveUser(User user);
    User findByEmail(String email) throws UserNotFoundException;
    User assignTask(String title,String email) throws TaskNotFoundException, UserNotFoundException;
}
