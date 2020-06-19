package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.CarGrade;

import java.util.List;

public interface CarGradeRepository extends JpaRepository<CarGrade,Long> {
    List<CarGrade> findByCarId(Long l);

}
