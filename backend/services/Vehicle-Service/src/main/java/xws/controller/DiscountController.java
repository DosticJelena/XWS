package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xws.dto.request.DiscountDTO;
import xws.dto.request.VehicleDiscountDTO;
import xws.service.DiscountService;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "/discount",produces = "application/json")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @RequestMapping(method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<?> addDiscountToVehicle(@RequestBody VehicleDiscountDTO request) {
        return new ResponseEntity<>(discountService.addDiscountToVehicle(request.getVehicleId(),request.getDiscountId()), HttpStatus.ACCEPTED);

    }
    @RequestMapping(value="new", method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<?> createNewDiscount(@RequestBody DiscountDTO request) {
        return new ResponseEntity<>(discountService.addNewDiscount(request),HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllDiscounts() {
        return new ResponseEntity<>(discountService.findAll(),HttpStatus.ACCEPTED);
    }
}
