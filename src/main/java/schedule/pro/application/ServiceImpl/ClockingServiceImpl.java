package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Clocking;
import schedule.pro.application.Entity.Dto.RequestClockingDto;
import schedule.pro.application.Entity.Task;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.InvalidClockingException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.ClockingRepository;
import schedule.pro.application.Repository.TaskRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.ClockingService;
import schedule.pro.application.Utils.DtoMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class ClockingServiceImpl implements ClockingService {
    ClockingRepository clockingRepository;
    UserRepository userRepository;
    TaskRepository taskRepository;

    @Autowired
    public ClockingServiceImpl(ClockingRepository clockingRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.clockingRepository = clockingRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }




    @Override
    public HttpStatus createNewClocking(RequestClockingDto clockingDto) throws UserNotFoundException, InvalidClockingException, TaskNotFoundException {
        Task task = taskRepository.findByTitle(clockingDto.getTaskName()).orElseThrow(() -> new TaskNotFoundException());
        User user = userRepository.findById(clockingDto.getUserId()).orElseThrow(() -> new UserNotFoundException());
        if(!validateClocking(clockingDto)){
            throw new InvalidClockingException();
        }
        Clocking clocking = DtoMapper.FromDtoToClocking(clockingDto);
        user.addClocking(clocking);
        clocking.setUser(user);
        task.addClocking(clocking);
        float hours = clockingDto.getHours();
        task.setTotalHours(task.getTotalHours()+ hours);
        clocking.setTask(task);
        clockingRepository.save(clocking);
        userRepository.save(user);
        taskRepository.save(task);
        return HttpStatus.OK;
    }

    public boolean validateClocking(RequestClockingDto clockingDto){
        int day = clockingDto.getDay();
        int hours= clockingDto.getHours();
        int month =clockingDto.getMonth();
        int year = clockingDto.getYear();
        if(hours >12){
            return false;
        }
        if(day>31||month>12){
            return false;
        }
        if(year!= 2023){
            return false;
        }
        return true;
    }
}
