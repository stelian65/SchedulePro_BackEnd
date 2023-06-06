package schedule.pro.application.Service;

import org.springframework.http.HttpStatus;
import schedule.pro.application.Entity.Dto.RequestClockingDto;
import schedule.pro.application.Exception.InvalidClockingException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;


public interface ClockingService {
    HttpStatus createNewClocking(RequestClockingDto clocking) throws UserNotFoundException, InvalidClockingException, TaskNotFoundException;

}
