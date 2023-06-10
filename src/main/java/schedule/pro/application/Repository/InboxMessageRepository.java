package schedule.pro.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import schedule.pro.application.Entity.InboxMessage;

import java.nio.file.LinkOption;
import java.util.List;
import java.util.Optional;

public interface InboxMessageRepository extends JpaRepository<InboxMessage, Long> {
    List<InboxMessage> findByUserId(Long id);
    InboxMessage save(InboxMessage message);
    void deleteByUserId(Long id);
    void deleteById(Long id);
    List<InboxMessage>  save( List<InboxMessage> messages);
}
