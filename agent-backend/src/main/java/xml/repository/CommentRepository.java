package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getAllByCarId(Long l);
    Comment findOneById(Long l);
    List<Comment> getAllByUserId(Long l);
    List<Comment> getAllByStatus(String status);
}