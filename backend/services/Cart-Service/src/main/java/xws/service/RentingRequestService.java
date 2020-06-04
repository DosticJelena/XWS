package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.request.CreateRentingRequestRequestDTO;
import xws.dto.response.RentingRequestResponseDTO;
import xws.model.Cart;
import xws.model.RentingRequest;
import xws.model.RentingRequestVehicle;
import xws.model.VehicleCart;
import xws.repository.RentingRequestRepository;
import xws.repository.RentingRequestVehicleRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Service
public class RentingRequestService {

    @Autowired
    private RentingRequestRepository rentingRequestRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private RentingRequestVehicleRepository rentingRequestVehicleRepository;

    public List<RentingRequestResponseDTO> findAllById(Long id) {
        List<RentingRequest> rentingRequests = rentingRequestRepository.findByUserId(id);
        List<RentingRequestResponseDTO> response = new ArrayList<>();
        for(RentingRequest r : rentingRequests){
            RentingRequestResponseDTO responseDTO = new RentingRequestResponseDTO();
            responseDTO.setId(r.getId());
            responseDTO.setUserId(r.getUserId());
            responseDTO.setVehicles(r.getVehicles());
            response.add(responseDTO);

        }
        return response;
    }
    public List<RentingRequest> createOneRequestPerVehicle(CreateRentingRequestRequestDTO requestDTO) {
        Cart cart = cartService.findOneById(requestDTO.getCartId());
        List<RentingRequest> response = new ArrayList<>();
        for(VehicleCart v : cart.getVehicles()){
            RentingRequest r = new RentingRequest();
            r.setStatus(RentingRequest.Status.PENDING);
            Long userId = cart.getUserId();
            r.setUserId(userId);
            RentingRequestVehicle rrv = new RentingRequestVehicle();
            rrv.setId(new RentingRequestVehicle.RentingRequestVehicleId(r.getId(),v.getId()));
            rrv.setRentingRequest(r);
            rrv.setVehicle(v);
            rrv.setStartDate(LocalDateTime.of(2020,10,10,0,0));
            rrv.setEndDate(LocalDateTime.of(2020,11,10,0,0));
            rentingRequestRepository.save(r);
            rentingRequestVehicleRepository.save(rrv);
            response.add(r);
        }
        cart.setVehicles(new HashSet<>());
        cartService.save(cart);
        return response;

    }
    public List<RentingRequest> createBundlePerOwner(CreateRentingRequestRequestDTO requestDTO) {
        Cart cart = cartService.findOneById(requestDTO.getCartId());
        HashMap<Long,ArrayList<VehicleCart>> hashMap = new HashMap<>();
        List<RentingRequest> response = new ArrayList<>();
        for(VehicleCart v : cart.getVehicles()) {
            hashMap.put(v.getOwnerId(),new ArrayList<>());
        }
        for(VehicleCart v : cart.getVehicles()) {
            hashMap.get(v.getOwnerId()).add(v);
        }
        for(Long l : hashMap.keySet()){
            RentingRequest r = new RentingRequest();
            r.setStatus(RentingRequest.Status.PENDING);
            Long userId = cart.getUserId();
            r.setUserId(userId);
            rentingRequestRepository.save(r);
            for(VehicleCart v : hashMap.get(l)) {
                RentingRequestVehicle rrv = new RentingRequestVehicle();
                rrv.setId(new RentingRequestVehicle.RentingRequestVehicleId(r.getId(),v.getId()));
                rrv.setRentingRequest(r);
                rrv.setVehicle(v);
                rrv.setStartDate(LocalDateTime.of(2020,10,10,0,0));
                rrv.setEndDate(LocalDateTime.of(2020,11,10,0,0));

                rentingRequestVehicleRepository.save(rrv);

            }
            rentingRequestRepository.save(r);
            response.add(r);
        }
        cart.setVehicles(new HashSet<>());
        cartService.save(cart);
        return response;
    }
}
