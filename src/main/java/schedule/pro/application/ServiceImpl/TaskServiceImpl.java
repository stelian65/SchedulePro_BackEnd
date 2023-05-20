package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Service.TaskService;

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
}
