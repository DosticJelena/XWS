package xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.model.Message;
import xml.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllByReceiverIdOrSenderId(Long receiver, Long sender){
        return messageRepository.findAllByReceiverIdOrSenderId(receiver,sender);
    }

    public Message save(Message m){
        return messageRepository.save(m);
    }
}