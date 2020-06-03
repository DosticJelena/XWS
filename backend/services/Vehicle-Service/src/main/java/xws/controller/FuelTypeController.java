package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.model.FuelType;
import xws.service.FuelTypeService;

@RestController
@RequestMapping(value = "fuel-type", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    FuelTypeService fuelTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(fuelTypeService.findAll(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "new", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> addVehicleModel(@RequestBody fuelTypeRequest request) {
        FuelType ft = new FuelType();
        ft.setFuelType(request.fuelTypeName);
        ft.setStatus(FuelType.Status.ACTIVE);

        return new ResponseEntity<>(fuelTypeService.save(ft), HttpStatus.CREATED);
    }

    public static class fuelTypeRequest {
        public String fuelTypeName;
    }
}
