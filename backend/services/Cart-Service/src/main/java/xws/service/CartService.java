package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.controller.CartController;
import xws.dto.request.AddVehicleToCartRequestDTO;
import xws.dto.request.CartRequestDTO;
import xws.dto.response.VehiclesInCartDTOResponse;
import xws.model.Cart;
import xws.model.VehicleCart;
import xws.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    VehicleService vehicleService;

    public Cart findOneById(Long id) {
        try {
            return  this.cartRepository.findOneById(id).orElseThrow(() -> new Exception());

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
    public VehiclesInCartDTOResponse addVehicleToCart(AddVehicleToCartRequestDTO requestDTO) {
        Cart c = this.findOneByUserId(requestDTO.getUserId());
        VehicleCart v = vehicleService.findOneById(requestDTO.getVehicleId());
        c.getVehicles().add(v);
        cartRepository.save(c);
        VehiclesInCartDTOResponse response = new VehiclesInCartDTOResponse();
        response.setUserId(c.getUserId());
        response.setCartId(c.getId());
        response.setVehicles(c.getVehicles());
        return response;
    }
    public VehiclesInCartDTOResponse getAllVehicles(Long id) {
        Cart c = this.findOneByUserId(id);
        VehiclesInCartDTOResponse response = new VehiclesInCartDTOResponse();
        response.setUserId(c.getUserId());
        response.setCartId(c.getId());
        response.setVehicles(c.getVehicles());
        return response;
    }
    public Cart save(CartRequestDTO requestDTO){
        Cart c = new Cart();
        c.setUserId(requestDTO.getUserId());
        return cartRepository.save(c);
    }
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

}
