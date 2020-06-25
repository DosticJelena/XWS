package xws.controller;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.dto.request.AddVehicleToCartRequestDTO;
import xws.dto.request.CartRequestDTO;
import xws.feignClients.VehicleServiceProxy;
import xws.model.Cart;
import xws.service.CartService;

import java.awt.print.Pageable;

@RestController
@RequestMapping(value = "")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    VehicleServiceProxy vehicleServiceProxy;

    /**
     * pronalazenje korpe koristeci korisnikov Id
      * @param id
     * @return
     */
    @RequestMapping(value = "vehicles/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getAllVehicles(id).getBody(), HttpStatus.ACCEPTED);
    }

    /**
     * Dodavanje vozila u korpu,koristeci id vozila i id korisnika
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/vehicle",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> addVehicleToCartDTO(@RequestBody AddVehicleToCartRequestDTO requestDTO) {
        return new ResponseEntity<>(cartService.addVehicleToCart(requestDTO),HttpStatus.ACCEPTED);
    }

    /**
     * Pravljenje nove korpe za korisnika,ova metoda bi trebalo da se pozove kada se registruje novi korisnik te da se u isto vreme napravi i korpa koju ce on koristiti
     * @param id
     * @return
     */
    @RequestMapping(value="/", method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> addCart(@RequestBody Long id){
        return new ResponseEntity<>(cartService.save(id),HttpStatus.CREATED);
    }


}
