package schedule.pro.application.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import schedule.pro.application.Entity.Dto.CreateInboxMessageDto;
import schedule.pro.application.Entity.Dto.CreateRequestDto;
import schedule.pro.application.Entity.Dto.RequestResponse;
import schedule.pro.application.Entity.Request;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.RequestNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.RequestRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.InboxMessageService;
import schedule.pro.application.Service.RequestService;
import schedule.pro.application.Utils.DtoMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class RequestServiceImpl implements RequestService {

    private RequestRepository requestRepository;
    private UserRepository userRepository;
    private InboxMessageService inboxMessageService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,UserRepository userRepository,InboxMessageService inboxMessageService) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.inboxMessageService=inboxMessageService;
    }

    @Override
    public CreateRequestDto createRequest(CreateRequestDto requestDto) throws UserNotFoundException {
        Request request = DtoMapper.fromCreateRequestDtoToRequest(requestDto);
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new UserNotFoundException());
        user.addRequest(request);
        request.setUser(user);
        userRepository.save(user);
        return  requestDto;
    }

    //TO DO : Check the request

    @Override
    public boolean responseRequest(RequestResponse response) throws RequestNotFoundException, UserNotFoundException {
        Request request =requestRepository.findById(response.getRequestId()).orElseThrow(() -> new RequestNotFoundException());
        request.setPending(false);
        request.setResponse(response.isResponse());
        Integer userId = request.getUser().getId();
        CreateInboxMessageDto messageDto = CreateInboxMessageDto.builder()
                .userId(userId)
                .message(createMessage(request))
                .build();
        inboxMessageService.saveMessage(messageDto);
        requestRepository.save(request);
        return true;
    }

    public String createMessage(Request request){
        String status;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = now.format(formatter);
        if(request.isResponse()){
            status="Accepted";
        } else{
            status="Declined";
        }

        return "Dear "+request.getUser().getFirstname()+" "+request.getUser().getLastname() +"\n\n" +
               "I am pleased to inform you that your request has been " +status+ " at:" + formatDateTime;
    }
}
