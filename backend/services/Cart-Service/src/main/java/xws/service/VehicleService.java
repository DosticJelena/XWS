package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.VehicleCart;
import xws.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;


    public VehicleCart findOneById(Long id) {
        try {
            return this.vehicleRepository.findOneById(id).orElseThrow(() -> new Exception());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
