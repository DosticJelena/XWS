package xws.service;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import xws.dto.CommentDTO;
import xws.feignClient.AuthServiceProxy;
import xws.model.Comment;
import xws.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthServiceProxy authServiceProxy;

    public List<Comment> getByStatus(String status){
        return commentRepository.getAllByStatus(status);
    }
    public List<Comment> getByUser(Long l){
        return commentRepository.getAllByUserId(l);
    }
    public List<CommentDTO> getByCar(Long l){
        List<CommentDTO> dtos = new ArrayList();
        for (Comment c : commentRepository.getAllByCarId(l)) {
            dtos.add(new CommentDTO(authServiceProxy.getUsername(c.getUserId()),c.getCarId(),c.getText(),c.getStatus())); //TODO FeignClient getUser
        }
        return dtos;
    }
    public Comment approve(Comment c){
        Comment upDate = commentRepository.findOneById(c.getId());
        upDate.setStatus("ACTIVE");
        return commentRepository.save(upDate);
    }
    public Comment reject(Comment c){
        Comment upDate = commentRepository.findOneById(c.getId());
        upDate.setStatus("CANCELED");
        return commentRepository.save(upDate);
    }
    public Comment save(Comment c){
        c.setStatus("PENDING");
        return commentRepository.save(c);
    }
    public Comment save(com.baeldung.springsoap.gen.Comment c){
        Comment newComm = new Comment();
        newComm.setText(c.getText());
        newComm.setCarId(c.getCarId());
        newComm.setUserId(c.getUserId());
        newComm.setStatus("PENDING");
        return commentRepository.save(newComm);
    }
}
