package schedule.pro.application.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Dto.CreateRequestDto;
import schedule.pro.application.Entity.Dto.RequestDto;
import schedule.pro.application.Entity.Dto.RequestResponse;
import schedule.pro.application.Entity.Request;
import schedule.pro.application.Exception.RequestNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Service.RequestService;

import java.util.List;

@Controller
@RequestMapping("/api/request")
public class RequestController {

    private RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseBody
    public CreateRequestDto createRequest(@RequestBody CreateRequestDto request) throws UserNotFoundException {
       return requestService.createRequest(request);
    }

    @PutMapping("/response")
    @ResponseBody
    public boolean responseRequest(@RequestBody RequestResponse response) throws RequestNotFoundException, UserNotFoundException {
        return requestService.responseRequest(response);
    }
    @GetMapping("/pending")
    @ResponseBody
    public List<RequestDto> getPendingRequests(){
        List<RequestDto> requests = requestService.getAllPeddingRequest();
        return requests;
    }
}
