package schedule.pro.application.Service;

import schedule.pro.application.Entity.Dto.CreateRequestDto;
import schedule.pro.application.Entity.Dto.CreateTaskDto;
import schedule.pro.application.Entity.Dto.RequestResponse;
import schedule.pro.application.Exception.RequestNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;

public interface RequestService {
    CreateRequestDto createRequest(CreateRequestDto request) throws UserNotFoundException;
    boolean responseRequest(RequestResponse response) throws RequestNotFoundException, UserNotFoundException;
}
