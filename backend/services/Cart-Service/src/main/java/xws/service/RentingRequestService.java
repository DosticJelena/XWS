package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.response.RentingRequestResponseDTO;
import xws.model.RentingRequest;
import xws.repository.RentingRequestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentingRequestService {

    @Autowired
    private RentingRequestRepository rentingRequestRepository;

    public List<RentingRequestResponseDTO> findAllById(Long id) {
        List<RentingRequest> rentingRequests = rentingRequestRepository.findAllByUserId(id);
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
}
