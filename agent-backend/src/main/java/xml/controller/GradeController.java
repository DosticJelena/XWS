package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.model.CarGrade;
import xml.service.CarGradeService;

@RestController
@RequestMapping(value = "grade")
public class GradeController {

    @Autowired
    private CarGradeService carGradeService;

    @RequestMapping(value = "",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCommentByStatus(@RequestParam("id") Long carId) {
        return new ResponseEntity<>(carGradeService.getGrades(carId), HttpStatus.OK);
    }
    @RequestMapping(value = "",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    public CarGrade makeGrade(@RequestBody CarGrade c) {
        return carGradeService.insertGrade(c);
    }

}