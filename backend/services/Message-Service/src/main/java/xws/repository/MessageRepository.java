package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.UserMessage;

import java.util.List;

public interface MessageRepository extends JpaRepository<UserMessage,Long> {
        List<UserMessage> findAllByReceiverIdOrSenderId(Long receiver, Long sender);
}
