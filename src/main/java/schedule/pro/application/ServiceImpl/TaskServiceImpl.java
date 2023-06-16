package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.EditTaskDto;
import schedule.pro.application.Entity.Dto.TaskDto;
import schedule.pro.application.Entity.Dto.UpdateTaskStatusDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.TaskStatus;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.InvalidTaskException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.TaskService;
import schedule.pro.application.Utils.DtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CreateTaskDto saveTask(CreateTaskDto taskRequest) throws UserNotFoundException, InvalidTaskException {
        validateTask(taskRequest);
        Task task = Task.builder()
                .status(TaskStatus.OPEN)
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .dueDate(taskRequest.getDueDate())
                .totalHours(0)
                .build();
        if(taskRequest.getUserId()!= null) {
            User user = userRepository.findById(taskRequest.getUserId()).orElseThrow(() -> new UserNotFoundException());
            user.addTask(task);
            task.setUser(user);
            userRepository.save(user);
        }else {
            taskRepository.save(task);
        }


        return taskRequest;
    }

    public boolean validateTask(CreateTaskDto task ) throws InvalidTaskException {
        if(task.getTitle() == null || task.getDueDate() == null || task.getDescription() == null){
            throw  new InvalidTaskException("Complete all the fields!");
        }
        if(task.getTitle().length()>40 || task.getTitle().length()<5){
            throw  new InvalidTaskException("The title should be more than 5 characters and less than 40!");
        }
        if(taskRepository.existsByTitle(task.getTitle())){
            throw  new InvalidTaskException("A task already exists with this title!");
        }
        if(task.getDescription().length() < 30 || task.getDescription().length() > 500){
            throw  new InvalidTaskException("The description should be more than 30 characters and less than 500!");
        }
        return  true;
    }

    public boolean validateTaskEdit(EditTaskDto task ) throws InvalidTaskException {
        if(task.getTitle() == null || task.getDueDate() == null || task.getDueDate().isEmpty() || task.getDescription() == null || task.getStatus() ==null){
            throw  new InvalidTaskException("Complete all the fields!");
        }
        if(task.getTitle().length()>40 || task.getTitle().length()<5){
            throw  new InvalidTaskException("The title should be more than 5 characters and less than 40!");
        }
        if(task.getDescription().length() < 30 || task.getDescription().length() > 500){
            throw  new InvalidTaskException("The description should be more than 30 characters and less than 500!");
        }
        return  true;
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

    @Override
    public long editTask(EditTaskDto taskDto) throws TaskNotFoundException, UserNotFoundException, InvalidTaskException {
        Task task = taskRepository.findById(taskDto.getTaskId()).orElseThrow(() -> new TaskNotFoundException());
        validateTaskEdit(taskDto);
        if(!task.getTitle().equals(taskDto.getTitle())){
            if(taskRepository.existsByTitle(taskDto.getTitle())){
                throw new InvalidTaskException("A task already exists with this title!");
            }
        }
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(taskDto.getStatus());
        if(taskDto.getUserId() != null){
            User user = userRepository.findById(taskDto.getUserId()).orElseThrow( () -> new UserNotFoundException() );
            user.addTask(task);
            task.setUser(user);
            userRepository.save(user);
        }else{
          task = taskRepository.save(task);
        }

        return task.getId();
    }

    @Override
    public boolean updateStatus(UpdateTaskStatusDto updateTaskStatusDto) throws TaskNotFoundException {
        Task task = taskRepository.findById(updateTaskStatusDto.getId()).orElseThrow(() -> new TaskNotFoundException());
        task.setStatus(updateTaskStatusDto.getStatus());
        taskRepository.save(task);
        return true;
    }


}
