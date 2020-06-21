package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xml.dto.request.NewVehicleRequestDTO;
import xml.model.Vehicle;
import xml.service.VehicleService;

import java.util.List;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Vehicle> allVehicles() {
        return vehicleService.getAll();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Vehicle getById(@PathVariable Long id) {
        return vehicleService.getById(id);

    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Vehicle newVehicle(@RequestBody NewVehicleRequestDTO request) {
        return vehicleService.save(request);

    }

}
