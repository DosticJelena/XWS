package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.dto.request.NewVehicleRequestDTO;
import xml.model.Vehicle;
import xml.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

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

    public static class Id {
        public Long id;
    }
}
