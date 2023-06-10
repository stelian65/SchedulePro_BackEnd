package schedule.pro.application.ServiceImpl;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schedule.pro.application.Entity.Dto.CreateInboxMessageDto;
import schedule.pro.application.Entity.InboxMessage;
import schedule.pro.application.Entity.User;
import schedule.pro.application.Exception.MessagesNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;
import schedule.pro.application.Repository.InboxMessageRepository;
import schedule.pro.application.Repository.UserRepository;
import schedule.pro.application.Service.InboxMessageService;

import java.util.List;
@Service
public class InboxMessageServiceImpl implements InboxMessageService {

    UserRepository userRepository;
    InboxMessageRepository inboxMessageRepository;

    @Autowired
    public InboxMessageServiceImpl(UserRepository userRepository, InboxMessageRepository inboxMessageRepository) {
        this.userRepository = userRepository;
        this.inboxMessageRepository = inboxMessageRepository;
    }

    @Override
    public List<InboxMessage> setMessagesAsShown(Long userId) throws MessagesNotFoundException {
        List<InboxMessage> messages = inboxMessageRepository.findByUserId(userId);
        if(messages == null){
            throw new MessagesNotFoundException();
        }
        messages.forEach((message) ->{
            message.setShown(true);
            inboxMessageRepository.save(message);
        });
        return messages;
    }

    @Override
    public InboxMessage saveMessage(CreateInboxMessageDto message) throws UserNotFoundException {
        InboxMessage inboxMessage = InboxMessage.builder()
                .message(message.getMessage())
                .shown(false)
                .build();
        User user = userRepository.findById(message.getUserId()).orElseThrow(() -> new UserNotFoundException());
        user.addMessage(inboxMessage);
        inboxMessage.setUser(user);
        userRepository.save(user);
        return  inboxMessage;

    }

    @Override
    @Transactional
    public boolean deleteAll(Long userId){
        inboxMessageRepository.deleteByUserId(userId);
        return true;
    }

    @Override
    public boolean deleteTheMessage(Long messageId) {
        inboxMessageRepository.deleteById(messageId);
        return true;
    }

    @Override
    public List<InboxMessage> getAllUserMessages(Long userId) {
        List<InboxMessage> inboxMessages = inboxMessageRepository.findByUserId(userId);
        return  inboxMessages;
    }


}
