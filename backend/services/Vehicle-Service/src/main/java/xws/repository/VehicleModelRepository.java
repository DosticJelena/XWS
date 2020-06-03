package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.VehicleModel;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    List<VehicleModel> findAll();
    VehicleModel findOneById(Long id);
    VehicleModel findOneByVehicleModel(String vehicleModel);
    VehicleModel save(VehicleModel vm);
}
