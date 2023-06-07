package schedule.pro.application.Service;


import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.TaskDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    CreateTaskDto saveTask(CreateTaskDto task);
    List<String> getTaskNamesByEmail(String email) throws TaskNotFoundException;
    List<TaskDto> getAllTasks();
}
