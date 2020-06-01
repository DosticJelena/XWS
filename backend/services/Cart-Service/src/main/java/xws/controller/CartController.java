package xws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.dto.request.AddVehicleToCartRequestDTO;
import xws.model.Cart;
import xws.service.CartService;

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.findOneByUserId(id).getVehiclesInCart(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> addVehicleToCartDTO(@RequestBody AddVehicleToCartRequestDTO requestDTO) {
        return new ResponseEntity<>(cartService.addVehicleToCart(requestDTO),HttpStatus.ACCEPTED);
    }

}
