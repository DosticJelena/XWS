package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateVehicleModel(String vehicleModel, Long id) {
        return vehicleModelRepository.updateVehicleModel(vehicleModel, id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteVehicleModel(Long id) {
        return vehicleModelRepository.deleteVehicleModel(id);
    }
}
