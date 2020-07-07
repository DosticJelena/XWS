package xws.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.UserMessage;
import xws.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<UserMessage> findAllByReceiverIdOrSenderId(Long receiver, Long sender){
        return messageRepository.findAllByReceiverIdOrSenderId(receiver,sender);
    }

    public UserMessage save(UserMessage m){
        return messageRepository.save(m);
    }
}
