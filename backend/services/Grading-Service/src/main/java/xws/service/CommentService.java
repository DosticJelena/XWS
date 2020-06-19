package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.Comment;
import xws.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getByStatus(String status){
        return commentRepository.getAllByStatus(status);
    }
    public List<Comment> getByUser(Long l){
        return commentRepository.getAllByUserId(l);
    }
    public List<Comment> getByCar(Long l){
        return commentRepository.getAllByCarId(l);
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
}
