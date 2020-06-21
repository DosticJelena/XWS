package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.dto.request.NewVehicleRequestDTO;
import xml.model.CarGrade;
import xml.model.Comment;
import xml.model.Vehicle;
import xml.repository.CarGradeRepository;
import xml.repository.CommentRepository;
import xml.service.VehicleService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CarGradeRepository carGradeRepository;

    @Autowired
    CommentRepository commentRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Vehicle newVehicle(@RequestBody NewVehicleRequestDTO request) {
        return vehicleService.save(request);

    }

    @RequestMapping(value = "/most-distance", method = RequestMethod.GET, produces = "application/json")
    public Vehicle mostDistance(@RequestBody Id id) {
        List<Vehicle> all = vehicleService.findAllByOwner_id(id.id);
        if(all.size() == 0) {
            return null;
        }

        double max = all.get(0).getDistance();
        Vehicle ret = all.get(0);

        for(Vehicle v : all) {
            if(v.getDistance() > max) {
                max = v.getDistance();
                ret = v;
            }
        }

        return ret;
    }

    @RequestMapping(value = "/most-comments", method = RequestMethod.GET, produces = "application/json")
    public Vehicle mostComments(@RequestBody Id id) {
        List<Vehicle> all = vehicleService.findAllByOwner_id(id.id);
        if(all.size() == 0) {
            return null;
        }

        ArrayList<List<Comment>> comments = new ArrayList<List<Comment>>();

        for(Vehicle v: all) {
            comments.add(commentRepository.findAllByCarId(v.getId()));
        }

        Vehicle ret = null;
        int maxComments = 0;

        for(int i=0;i<comments.size();i++) {
            List<Comment> l = comments.get(i);

            if (l.size() > maxComments) {
                maxComments = l.size();
                ret = vehicleService.findOneById(l.get(0).getCarId());
            }
        }

        return ret;
    }

    @RequestMapping(value = "/best-grade", method = RequestMethod.GET, produces = "application/json")
    public Vehicle bestGrade(@RequestBody Id id) {
        List<Vehicle> all = vehicleService.findAllByOwner_id(id.id);
        if(all.size() == 0) {
            return null;
        }

        ArrayList<List<CarGrade>> grades = new ArrayList<List<CarGrade>>();

        for(Vehicle v: all) {
            grades.add(carGradeRepository.findAllByCarId(v.getId()));
        }

        Vehicle ret = null;
        double maxGrade = 0;

        for(int i=0;i<grades.size();i++) {
            List<CarGrade> l = grades.get(i);
            int gr = 0;
            for (CarGrade cg : l) {
                gr += cg.getValue();
            }

            double g = gr / l.size();
            if (g > maxGrade) {
                maxGrade = g;
                ret = vehicleService.findOneById(l.get(0).getCarId());
            }
        }

        return ret;
    }

    public static class Id {
        public Long id;
    }
}
