package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.model.UserMessage;
import xws.services.MessageService;

@RestController
@RequestMapping(value = "")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/{user}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@PathVariable("user") Long user) {
        return new ResponseEntity<>(messageService.findAllByReceiverIdOrSenderId(user,user), HttpStatus.ACCEPTED);
    }
    @RequestMapping(value = "/",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public UserMessage sendMessage(@RequestBody UserMessage m) {
        return messageService.save(m);
    }
}
