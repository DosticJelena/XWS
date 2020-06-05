package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.RentingRequestVehicle;

import java.time.LocalDateTime;
import java.util.List;

public interface RentingRequestVehicleRepository extends JpaRepository<RentingRequestVehicle, RentingRequestVehicle.RentingRequestVehicleId> {
    List<RentingRequestVehicle> findByVehicleId(Long id);
}
