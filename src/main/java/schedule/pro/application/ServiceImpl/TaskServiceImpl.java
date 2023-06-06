package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Service.TaskService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<String> getTaskNamesByUsername(String username) throws TaskNotFoundException {
        List<Task> tasks=  taskRepository.findByUserUsername(username);
        if(tasks.isEmpty()){
           throw  new TaskNotFoundException();
        }
        List<String> taskNames = new ArrayList<>();
        tasks.forEach((task)->taskNames.add(task.getName()));
        return taskNames;
    }
}
