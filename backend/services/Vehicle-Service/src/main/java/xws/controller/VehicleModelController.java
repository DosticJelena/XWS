package xws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.model.VehicleClass;
import xws.model.VehicleModel;
import xws.service.VehicleModelService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "vehicle-model", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleModelController {

    @Autowired
    VehicleModelService vehicleModelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<VehicleModel> ret = new ArrayList<>();

        for(VehicleModel vm : vehicleModelService.findAll()) {
            if(vm.getStatus().equals(VehicleModel.Status.ACTIVE)) {
                ret.add(vm);
            }
        }

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleModel(@RequestBody vehicleModelRequest request) {
        VehicleModel vm = new VehicleModel();
        vm.setModel(request.model);
        vm.setBrand(request.brand);
        vm.setStatus(VehicleModel.Status.ACTIVE);

        return new ResponseEntity<>(vehicleModelService.save(vm), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<?> updateVehicleModel(@RequestBody updateRequest request) {
        return new ResponseEntity<>(vehicleModelService.updateVehicleModel(request.newModel, request.newBrand, request.id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> deleteVehicleModel(@RequestBody Id request) {
        return new ResponseEntity<>(vehicleModelService.deleteVehicleModel(request.id), HttpStatus.ACCEPTED);
    }

    public static class vehicleModelRequest {
        public String model;
        public String brand;
    }

    public static class Id {
        public Long id;
    }

    public static class updateRequest {
        public String newModel;
        public String newBrand;
        public Long id;
    }
}
