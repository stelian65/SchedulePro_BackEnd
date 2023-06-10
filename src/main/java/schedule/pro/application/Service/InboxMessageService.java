package schedule.pro.application.Service;

import schedule.pro.application.Entity.Dto.CreateInboxMessageDto;
import schedule.pro.application.Entity.InboxMessage;
import schedule.pro.application.Exception.MessagesNotFoundException;
import schedule.pro.application.Exception.UserNotFoundException;

import java.util.List;

public interface InboxMessageService {
    List<InboxMessage> setMessagesAsShown(Long userId) throws MessagesNotFoundException;
    InboxMessage saveMessage(CreateInboxMessageDto message) throws UserNotFoundException;
    boolean deleteAll(Long userId) throws UserNotFoundException;
    boolean deleteTheMessage(Long messageId);
    List<InboxMessage> getAllUserMessages(Long userId);
}
