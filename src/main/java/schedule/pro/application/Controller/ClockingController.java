package schedule.pro.application.Controller;

import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import schedule.pro.application.Entity.Clocking;
import schedule.pro.application.Entity.Dto.RequestClockingDto;
import schedule.pro.application.Exception.InvalidClockingException;
import schedule.pro.application.Exception.TaskNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Service.ClockingService;

import java.net.http.HttpResponse;

@Controller
@RequestMapping(value = "/api/clocking")
public class ClockingController {

    ClockingService clockingService;

    @Autowired
    public ClockingController(ClockingService clockingService) {
        this.clockingService = clockingService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity addClocking(@RequestBody RequestClockingDto clockingDto) throws UserNotFoundException, InvalidClockingException, TaskNotFoundException {
        HttpStatus status = clockingService.createNewClocking(clockingDto);
        return new ResponseEntity(clockingDto,status);
    }
}
