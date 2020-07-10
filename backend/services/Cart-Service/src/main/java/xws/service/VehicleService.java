package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xws.dto.request.NewVehicleDTO;
import xws.dto.request.NewVehicleSagaDTO;
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
    public VehicleCart newVehicle(NewVehicleDTO request) {
        VehicleCart vehicle = new VehicleCart();
        vehicle.setOwnerId(request.getOwnerId());
        return vehicleRepository.save(vehicle);
    }

    @Transactional
    public VehicleCart save(NewVehicleSagaDTO newVehicle) {
        VehicleCart vehicleCart = new VehicleCart();
        vehicleCart.setOwnerId(newVehicle.getOwnerId());
        return vehicleRepository.save(vehicleCart);
    }
}
