package schedule.pro.application.Service;


import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.EditTaskDto;
import schedule.pro.application.Entity.Dto.TaskDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.InvalidTaskException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;

import java.util.List;

public interface TaskService {
    CreateTaskDto saveTask(CreateTaskDto task) throws UserNotFoundException, InvalidTaskException;
    List<String> getTaskNamesByEmail(String email) throws TaskNotFoundException;
    List<TaskDto> getAllTasks();
    TaskDto getTaskById(Long id) throws TaskNotFoundException;
    void deleteById(Long id ) throws TaskNotFoundException;
    long editTask(EditTaskDto taskDto ) throws  TaskNotFoundException,UserNotFoundException,InvalidTaskException;

}
