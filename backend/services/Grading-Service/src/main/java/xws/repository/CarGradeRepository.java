package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.CarGrade;

import java.util.List;

public interface CarGradeRepository extends JpaRepository<CarGrade,Long> {
    List<CarGrade> findByCarId(Long l);

}
