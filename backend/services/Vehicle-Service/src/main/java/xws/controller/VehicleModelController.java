package xws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.model.VehicleModel;
import xws.service.VehicleModelService;

import java.util.List;

@RestController
@RequestMapping(value = "vehicle-model", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleModelController {

    @Autowired
    VehicleModelService vehicleModelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(vehicleModelService.findAll(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleModel(@RequestBody vehicleModelRequest request) {
        VehicleModel vm = new VehicleModel();
        vm.setVehicleModel(request.vehicleModelName);
        vm.setStatus(VehicleModel.Status.ACTIVE);

        return new ResponseEntity<>(vehicleModelService.save(vm), HttpStatus.CREATED);
    }

    public static class vehicleModelRequest {
        public String vehicleModelName;
    }
}
