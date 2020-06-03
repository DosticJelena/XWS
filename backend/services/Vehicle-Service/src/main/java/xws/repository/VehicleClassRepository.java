package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xws.model.VehicleClass;

import java.util.List;

public interface VehicleClassRepository extends JpaRepository<VehicleClass, Long> {

    List<VehicleClass> findAll();
    VehicleClass findOneById(Long id);
    VehicleClass findOneByVehicleClass(String vehicleClass);
    VehicleClass save(VehicleClass vc);

    @Modifying
    @Transactional
    @Query("update VehicleClass vc set vc.vehicleClass = :vehicleClass where vc.id = :id")
    int updateVehicleClass(@Param("vehicleClass") String vehicleClass, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update VehicleClass vc set vc.status = 1 where vc.id = :id")
    int deleteVehicleClass(@Param("id") Long id);
}

