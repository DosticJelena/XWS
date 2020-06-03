package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.model.VehicleClass;
import xws.service.VehicleClassService;

@RestController
@RequestMapping(value = "vehicle-class", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleClassController {

    @Autowired
    VehicleClassService vehicleClassService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(vehicleClassService.findAll(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleClass(@RequestBody vehicleClassRequest request) {
        VehicleClass vc = new VehicleClass();
        vc.setVehicleClass(request.vehicleClassName);
        vc.setStatus(VehicleClass.Status.ACTIVE);

        return new ResponseEntity<>(vehicleClassService.save(vc), HttpStatus.CREATED);
    }

    public static class vehicleClassRequest {
        public String vehicleClassName;
    }
}
