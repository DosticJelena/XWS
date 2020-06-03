package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.model.FuelType;
import xws.service.FuelTypeService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "fuel-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        List<FuelType> ret = new ArrayList<>();

        for(FuelType ft : fuelTypeService.findAll()) {
            if(ft.getStatus().equals(FuelType.Status.ACTIVE)) {
                ret.add(ft);
            }
        }

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleModel(@RequestBody fuelTypeRequest request) {
        FuelType ft = new FuelType();
        ft.setFuelType(request.fuelTypeName);
        ft.setStatus(FuelType.Status.ACTIVE);

        return new ResponseEntity<>(fuelTypeService.save(ft), HttpStatus.CREATED);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> updateVehicleModel(@RequestBody updateRequest request) {
        return new ResponseEntity<>(fuelTypeService.updateFuelType(request.newName, request.id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> deleteVehicleModel(@RequestBody Id request) {
        return new ResponseEntity<>(fuelTypeService.deleteFuelType(request.id), HttpStatus.ACCEPTED);
    }

    public static class Id {
        public Long id;
    }

    public static class fuelTypeRequest {
        public String fuelTypeName;
    }

    public static class updateRequest {
        public String newName;
        public Long id;
    }
}
