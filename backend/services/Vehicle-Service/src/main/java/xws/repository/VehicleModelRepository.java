package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xws.model.VehicleModel;

import java.util.List;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Long> {

    List<VehicleModel> findAll();
    VehicleModel findOneById(Long id);
    //VehicleModel findOneByVehicleModel(String vehicleModel);
    VehicleModel save(VehicleModel vm);

    @Modifying
    @Transactional
    @Query("update VehicleModel vm set vm.model = :model, vm.brand = :brand where vm.id = :id")
    int updateVehicleModel(@Param("model") String model, @Param("brand") String brand, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update VehicleModel vm set vm.status = 1 where vm.id = :id")
    int deleteVehicleModel(@Param("id") Long id);
}
