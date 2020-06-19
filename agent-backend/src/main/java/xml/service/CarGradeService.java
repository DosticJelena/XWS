package xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.model.CarGrade;
import xml.repository.CarGradeRepository;

import java.util.List;

@Service
public class CarGradeService {

    @Autowired
    private CarGradeRepository carGradeRepository;

    public List<CarGrade> getGrades(Long l){return carGradeRepository.findByCarId(l);}

    public CarGrade insertGrade(CarGrade c){return carGradeRepository.save(c);}
}