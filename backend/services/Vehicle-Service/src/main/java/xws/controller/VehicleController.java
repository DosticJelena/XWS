package xws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.dto.request.NewVehicleRequestDTO;
import xws.model.Vehicle;
import xws.service.VehicleService;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public List<Vehicle> search(@RequestParam("location") String location,@RequestParam("startDate") String startDate,
                                @RequestParam("endDate") String endDate,@RequestParam("brand") String brand,
                                @RequestParam("model") String model,@RequestParam("fuel_type") String fuel_type,
                                @RequestParam("transmission") String transmission,@RequestParam("type") String type,
                                @RequestParam("minPrice") double minPrice,@RequestParam("maxPrice") double maxPrice,@RequestParam("distance") double distance,
                                @RequestParam("CDWStatus") String CDWStatus,@RequestParam("childrenSeats") int childrenSeats) {

        return vehicleService.search( location,startDate,endDate, brand, model, fuel_type, transmission, type, minPrice,maxPrice, distance, CDWStatus,childrenSeats);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public Vehicle getById(@PathVariable("id") Long id) {
        return vehicleService.getById(id);
    }

    @RequestMapping(value = "/owner/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<Vehicle> getByOwnerId(@PathVariable("id") Long id) {
        return vehicleService.getByOwnerId(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Vehicle newVehicle(@RequestBody NewVehicleRequestDTO request) {
        return vehicleService.save(request);

    }
    @RequestMapping(value = "test/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(@PathVariable("id") Long id) {
        return new ResponseEntity<>(vehicleService.getById(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/all/{ids}",method = RequestMethod.GET,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllvehiclesWithId(@PathVariable("ids") ArrayList<Long> ids) {
        return new ResponseEntity<>(vehicleService.findAllByIds(ids),HttpStatus.ACCEPTED);
    }


}
