package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.VehicleModel;
import xws.repository.VehicleModelRepository;

import java.util.List;

@Service
public class VehicleModelService {

    @Autowired
    VehicleModelRepository vehicleModelRepository;

    public List<VehicleModel> findAll() {
        return vehicleModelRepository.findAll();
    }

    public VehicleModel findOneById(Long id) {
        return vehicleModelRepository.findOneById(id);
    }

    public VehicleModel findOneByVehicleModel(String vehicleModel) {
        return vehicleModelRepository.findOneByVehicleModel(vehicleModel);
    }

    public VehicleModel save(VehicleModel vm) {
        return vehicleModelRepository.save(vm);
    }
}
