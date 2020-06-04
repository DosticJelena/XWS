package xws.controller;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.dto.request.CreateRentingRequestRequestDTO;
import xws.service.RentingRequestService;

@RestController
@RequestMapping(value = "/rentingRequest")
public class RentingRequestController {

    @Autowired
    private RentingRequestService rentingRequestService;

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(rentingRequestService.findAllById(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/create-one-per-vehicle",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> createOnePerVehicle(@RequestBody CreateRentingRequestRequestDTO requestDTO) {
        return new ResponseEntity<>(rentingRequestService.createOneRequestPerVehicle(requestDTO), HttpStatus.CREATED);
    }
    @RequestMapping(value = "/create-bundle-per-owner",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> createBundlePerOwner(@RequestBody CreateRentingRequestRequestDTO requestDTO) {
        return new ResponseEntity<>(rentingRequestService.createBundlePerOwner(requestDTO), HttpStatus.CREATED);
    }
}
