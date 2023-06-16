package schedule.pro.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import schedule.pro.application.Entity.InboxMessage;
import java.util.List;


@Repository
public interface InboxMessageRepository extends JpaRepository<InboxMessage, Long> {
    List<InboxMessage> findByUserId(Long id);
    InboxMessage save(InboxMessage message);
    void deleteByUserId(Long id);
    void deleteById(Long id);
    List<InboxMessage> save( List<InboxMessage> messages);
}
