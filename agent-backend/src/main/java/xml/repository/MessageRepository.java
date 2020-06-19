package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findAllByReceiverIdOrSenderId(Long receiver, Long sender);
}
