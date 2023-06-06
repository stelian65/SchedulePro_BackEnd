package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.TaskNotFoundException;
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
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    @Override
    public User assignTask(String name,String username) throws TaskNotFoundException {
        Task task = taskRepository.findByName(name).orElseThrow(() -> new TaskNotFoundException());
        User user = userRepository.findByUsername(username).get();
        user.addTask(task);
        task.setUser(user);
        taskRepository.save(task);
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
