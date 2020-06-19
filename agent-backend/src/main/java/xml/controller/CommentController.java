package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.model.Comment;
import xml.service.CommentService;

@RestController
@RequestMapping(value = "comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/user",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCommentByUser(@RequestParam("user") Long user) {
        return new ResponseEntity<>(commentService.getByUser(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/car",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCommentByCar(@RequestParam("car") Long car) {
        return new ResponseEntity<>(commentService.getByCar(car), HttpStatus.OK);
    }

    @RequestMapping(value = "/status",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCommentByStatus(@RequestParam("status") String status) {
        return new ResponseEntity<>(commentService.getByStatus(status), HttpStatus.OK);
    }

    @RequestMapping(value = "",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public Comment postComment(@RequestBody Comment c) {
        return commentService.save(c);
    }
}