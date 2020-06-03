package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xws.model.VehicleClass;
import xws.repository.VehicleClassRepository;

import java.util.List;

@Service
public class VehicleClassService {

    @Autowired
    VehicleClassRepository vehicleClassRepository;

    public List<VehicleClass> findAll() {
        return vehicleClassRepository.findAll();
    }

    public VehicleClass findOneById(Long id) {
        return vehicleClassRepository.findOneById(id);
    }

    public VehicleClass findOneByVehicleClass(String vehicleClass) {
        return vehicleClassRepository.findOneByVehicleClass(vehicleClass);
    }

    public VehicleClass save(VehicleClass vc) {
        return vehicleClassRepository.save(vc);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateVehicleClass(String vehicleClass, Long id) {
        return vehicleClassRepository.updateVehicleClass(vehicleClass, id);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int deleteVehicleClass(Long id) {
        return vehicleClassRepository.deleteVehicleClass(id);
    }
}
