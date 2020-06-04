package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
}
