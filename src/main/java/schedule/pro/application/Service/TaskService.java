package schedule.pro.application.Service;


import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.TaskNotFoundException;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    List<String> getTaskNamesByUsername(String username) throws TaskNotFoundException;
}
