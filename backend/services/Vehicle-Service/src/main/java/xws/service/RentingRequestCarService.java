package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.RentingRequestCar;
import xws.repository.RentingRequestCarRepository;

import java.util.List;

@Service
public class RentingRequestCarService {

    @Autowired
    RentingRequestCarRepository rentingRequestCarRepository;

    public List<RentingRequestCar> getByID(Long id){

        return rentingRequestCarRepository.findAllById(id);
    }
}
