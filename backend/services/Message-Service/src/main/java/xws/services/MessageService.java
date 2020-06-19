package xws.services;

import antlr.debug.MessageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.Message;
import xws.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllByReceiverIdOrSenderId(Long receiver,Long sender){
        return messageRepository.findAllByReceiverIdOrSenderId(receiver,sender);
    }

    public Message save(Message m){
        return messageRepository.save(m);
    }
}
