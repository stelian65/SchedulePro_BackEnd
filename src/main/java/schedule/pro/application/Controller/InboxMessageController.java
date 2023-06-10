package schedule.pro.application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import schedule.pro.application.Entity.Dto.CreateInboxMessageDto;
import schedule.pro.application.Entity.InboxMessage;
import schedule.pro.application.Exception.MessagesNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Service.InboxMessageService;

import java.util.List;

@Controller
@RequestMapping("/api/inbox")
public class InboxMessageController {

    InboxMessageService inboxMessageService;

    @Autowired
    public InboxMessageController(InboxMessageService inboxMessageService) {
        this.inboxMessageService = inboxMessageService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<InboxMessage> createInboxMessage(@RequestBody CreateInboxMessageDto message) throws UserNotFoundException {
        InboxMessage inboxMessage = inboxMessageService.saveMessage(message);
        return new ResponseEntity<>(inboxMessage, HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<Boolean> deleteAnMessage(@RequestParam Long messageId) {
        boolean done = inboxMessageService.deleteTheMessage(messageId);
        return  new ResponseEntity<>(done,HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/all")
    @ResponseBody
    public ResponseEntity<Boolean> deleteAllMessages(@RequestParam Long userId) throws UserNotFoundException {
        boolean done = inboxMessageService.deleteAll(userId);
        return  new ResponseEntity<>(done,HttpStatus.NO_CONTENT);
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity<List<InboxMessage>> getUserMessages(@RequestParam Long userId){
        List<InboxMessage> inboxMessages = inboxMessageService.getAllUserMessages(userId);
        return new ResponseEntity<>(inboxMessages,HttpStatus.OK);
    }

    @PutMapping("/read")
    @ResponseBody
    public ResponseEntity<List<InboxMessage>> redUserMessages(@RequestParam Long userId) throws MessagesNotFoundException {
        List<InboxMessage> inboxMessages = inboxMessageService.setMessagesAsShown(userId);
        return new ResponseEntity<>(inboxMessages,HttpStatus.OK);
    }

}
