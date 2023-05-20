package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {
    final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    @ResponseBody
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        Task taskR = taskService.saveTask(task);
        return new ResponseEntity<Task>(taskR, HttpStatus.OK);
    }
}
