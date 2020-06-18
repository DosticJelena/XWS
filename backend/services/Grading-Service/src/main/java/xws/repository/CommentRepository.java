package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import xws.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getAllByCarId(Long l);
    Comment findOneById(Long l);
    List<Comment> getAllByUserId(Long l);
    List<Comment> getAllByStatus(String status);

//    @@Modifying
//    @Transactional
//    @Query("update Room r set r.name=:name, r.number=:number where r.id=:id")
//    int updateRoom(@Param("name") String name, @Param("number") int number, @Param("id") Long id);
}
