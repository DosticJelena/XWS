package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.dto.request.NewVehicleDTO;
import xws.service.VehicleService;

@RestController
@RequestMapping(value = "/vehicle")
public class VehicleCartController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST,value="/new")
    public ResponseEntity<?> createNewVehicle(@RequestBody NewVehicleDTO request) {
        return new ResponseEntity<>(vehicleService.newVehicle(request), HttpStatus.ACCEPTED);
    }

}
