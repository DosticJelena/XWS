package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.model.VehicleClass;
import xws.service.VehicleClassService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "vehicle-class", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleClassController {

    @Autowired
    VehicleClassService vehicleClassService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<VehicleClass> ret = new ArrayList<>();

        for(VehicleClass vc : vehicleClassService.findAll()) {
            if(vc.getStatus().equals(VehicleClass.Status.ACTIVE)) {
                ret.add(vc);
            }
        }

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleClass(@RequestBody vehicleClassRequest request) {
        VehicleClass vc = new VehicleClass();
        vc.setVehicleClass(request.vehicleClassName);
        vc.setStatus(VehicleClass.Status.ACTIVE);

        return new ResponseEntity<>(vehicleClassService.save(vc), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> updateVehicleClass(@RequestBody updateRequest request) {
        return new ResponseEntity<>(vehicleClassService.updateVehicleClass(request.newName, request.id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> deleteVehicleClass(@RequestBody Id request) {
        return new ResponseEntity<>(vehicleClassService.deleteVehicleClass(request.id), HttpStatus.ACCEPTED);
    }

    public static class vehicleClassRequest {
        public String vehicleClassName;
    }

    public static class Id {
        public Long id;
    }

    public static class updateRequest {
        public String newName;
        public Long id;
    }
}
