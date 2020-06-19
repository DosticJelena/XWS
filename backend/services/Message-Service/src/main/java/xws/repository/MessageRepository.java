package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {
        List<Message> findAllByReceiverIdOrSenderId(Long receiver,Long sender);
}
