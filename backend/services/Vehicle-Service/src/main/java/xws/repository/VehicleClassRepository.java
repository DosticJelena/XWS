package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.VehicleClass;

import java.util.List;

public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {

    List<VehicleClass> findAll();
    VehicleClass findOneById(Long id);
    VehicleClass findOneByVehicleClass(String vehicleClass);
    VehicleClass save(VehicleClass vc);
}

