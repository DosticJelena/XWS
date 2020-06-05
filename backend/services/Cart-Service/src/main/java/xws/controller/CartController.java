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

    /**
     * pronalazenje korpe koristeci korisnikov Id
      * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getCart(@PathVariable Long id) {
        return new ResponseEntity<>(cartService.getAllVehicles(id), HttpStatus.ACCEPTED);
    }

    /**
     * Dodavanje vozila u korpu,koristeci id vozila i id korisnika
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> addVehicleToCartDTO(@RequestBody AddVehicleToCartRequestDTO requestDTO) {
        return new ResponseEntity<>(cartService.addVehicleToCart(requestDTO),HttpStatus.ACCEPTED);
    }

    /**
     * Pravljenje nove korpe za korisnika,ova metoda bi trebalo da se pozove kada se registruje novi korisnik te da se u isto vreme napravi i korpa koju ce on koristiti
     * @param requestDTO
     * @return
     */
    @RequestMapping( value = "/addCart",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> addCart(@RequestBody CartDTO requestDTO){
        return new ResponseEntity<>(cartService.save(requestDTO),HttpStatus.CREATED);
    }
    public static class CartDTO {
        public Long userId;
    }

}
