package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.UserService;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {


    UserRepository userRepository;
    TaskRepository taskRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,TaskRepository taskRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new UserNotFoundException());
    }


    @Override
    public User assignTask(String title,String email) throws TaskNotFoundException, UserNotFoundException {
        Task task = taskRepository.findByTitle(title).orElseThrow(() -> new TaskNotFoundException());
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        user.addTask(task);
        task.setUser(user);
        taskRepository.save(task);
        userRepository.save(user);
        return user;
    }


}
