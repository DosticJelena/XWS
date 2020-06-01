package xws.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xws.model.Vehicle;
import xws.service.VehicleService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
    public List<Vehicle> search(@RequestParam("location") String location,@RequestParam("startDate") String startDate,
                                            @RequestParam("endDate") String endDate,@RequestParam("brand") String brand,
                                            @RequestParam("model") String model,@RequestParam("fuel_type") String fuel_type,
                                            @RequestParam("transmission") String transmission,@RequestParam("type") String type,
                                            @RequestParam("price") double price,@RequestParam("distance") double distance,
                                            @RequestParam("CDWStatus") String CDWStatus,@RequestParam("childrenSeats") int childrenSeats) {


        return vehicleService.search( location, brand, model, fuel_type, transmission, type, price, distance, CDWStatus,childrenSeats);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Vehicle> getAll() {
        return vehicleService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Vehicle getById(@PathVariable Long id) {


        return vehicleService.getById(id);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public Vehicle newVehicle(Vehicle v) {
        return vehicleService.save(v);
    }

}
