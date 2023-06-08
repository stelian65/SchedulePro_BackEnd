package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.TaskDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.TaskStatus;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Service.TaskService;
import schedule.pro.application.Utils.DtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public CreateTaskDto saveTask(CreateTaskDto taskRequest) {
        Task task = Task.builder()
                .status(TaskStatus.OPEN)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .totalHours(0)
                .build();
        taskRepository.save(task);
        return taskRequest;
    }

    @Override
    public List<String> getTaskNamesByEmail(String email) throws TaskNotFoundException {
        List<Task> tasks=  taskRepository.findByUserEmail(email);
        if(tasks.isEmpty()){
            throw  new TaskNotFoundException();
        }
        List<String> taskNames = new ArrayList<>();
        tasks.forEach((task)->taskNames.add(task.getTitle()));
        return taskNames;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> tasksDto = tasks.stream()
                .map(DtoMapper::fromTaskToTaskDto)
                .collect(Collectors.toList());
        return tasksDto;
    }

    @Override
    public TaskDto getTaskById(Long id) throws TaskNotFoundException {
        Task task = taskRepository.findById(id).orElseThrow( ()-> new TaskNotFoundException());
        TaskDto taskDto = DtoMapper.fromTaskToTaskDto(task);
        return  taskDto;
    }

    @Override
    public void deleteById(Long id) throws TaskNotFoundException {
        if(!taskRepository.existsById(id)){
            throw  new TaskNotFoundException();
        }
        taskRepository.deleteById(id);
    }

}
