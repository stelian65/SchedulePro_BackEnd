package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Service.TaskService;

import java.util.List;

@Controller
@RequestMapping("/api/task")
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<String>> getTaskByUser(@RequestParam String username) throws TaskNotFoundException {
        List<String> taskNames = taskService.getTaskNamesByUsername(username);
        return new ResponseEntity<>(taskNames,HttpStatus.OK);
    }

}
