package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.request.AddVehicleToCartRequestDTO;
import xws.model.Cart;
import xws.model.CartVehicle;
import xws.model.Vehicle;
import xws.repository.CartRepository;
import xws.repository.CartVehicleRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    VehicleService vehicleService;

    @Autowired
    CartVehicleRepository cartVehicleRepository;

    public Cart findOneById(Long id) {
        try {
            return this.cartRepository.findOneById(id).orElseThrow(() -> new Exception());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Cart findOneByUserId(Long id){
        try {
            return this.cartRepository.findOneByUserId(id).orElseThrow(() -> new Exception());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public CartVehicle addVehicleToCart(AddVehicleToCartRequestDTO requestDTO) {
        CartVehicle cartVehicle = new CartVehicle();
        cartVehicle.setCart(this.findOneByUserId(requestDTO.getUserId()));
        cartVehicle.setVehicle(this.vehicleService.findOneById(requestDTO.getCarId()));
        return cartVehicleRepository.save(cartVehicle);

    }

}
