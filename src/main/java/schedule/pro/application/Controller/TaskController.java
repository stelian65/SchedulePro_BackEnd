package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.EditTaskDto;
import schedule.pro.application.Entity.Dto.TaskDto;
import schedule.pro.application.Entity.Dto.UpdateTaskStatusDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Exception.InvalidTaskException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
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
    public ResponseEntity<CreateTaskDto> createTask(@RequestBody CreateTaskDto task) throws UserNotFoundException, InvalidTaskException {
        CreateTaskDto taskR = taskService.saveTask(task);
        return new ResponseEntity<CreateTaskDto>(taskR, HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<String>> getTaskByUser(@RequestParam String email) throws TaskNotFoundException {
        List<String> taskNames = taskService.getTaskNamesByEmail(email);
        return new ResponseEntity<>(taskNames,HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        List<TaskDto> tasks =taskService.getAllTasks();
        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<TaskDto> getTaskById(@RequestParam long id) throws TaskNotFoundException {
        TaskDto task = taskService.getTaskById(id);
        return  new ResponseEntity<>(task,HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<String> deleteTaskById(@RequestParam long id ) throws TaskNotFoundException{
        taskService.deleteById(id);
        return new ResponseEntity<>("Delete with succes",HttpStatus.NO_CONTENT);
    }

    @PutMapping("/edit")
    @ResponseBody
    public ResponseEntity<Long> editTask(@RequestBody EditTaskDto task) throws UserNotFoundException, InvalidTaskException, TaskNotFoundException {
        long id = taskService.editTask(task);
        return  new ResponseEntity<>(id ,HttpStatus.OK);
    }

    @PutMapping("/status")
    @ResponseBody
    public ResponseEntity<Boolean> updateStatus(@RequestBody UpdateTaskStatusDto updateTaskStatusDto) throws TaskNotFoundException {
        boolean response = taskService.updateStatus(updateTaskStatusDto);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

}
