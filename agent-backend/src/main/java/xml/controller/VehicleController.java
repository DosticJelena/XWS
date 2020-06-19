package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.dto.request.NewVehicleRequestDTO;
import xml.model.Vehicle;
import xml.service.VehicleService;

@RestController
@RequestMapping(value = "vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Vehicle newVehicle(@RequestBody NewVehicleRequestDTO request) {
        return vehicleService.save(request);

    }

}
