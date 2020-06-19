package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.model.Message;
import xml.service.MessageService;

@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@RequestParam("user") Long user) {
        return new ResponseEntity<>(messageService.findAllByReceiverIdOrSenderId(user,user), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public Message sendMessage(@RequestBody Message m) {
        return messageService.save(m);
    }
}
