package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.RentingRequestVehicle;

import java.util.List;

public interface RentingRequestVehicleRepository extends JpaRepository<RentingRequestVehicle, RentingRequestVehicle.RentingRequestVehicleId> {
    List<RentingRequestVehicle> findByVehicleId(Long id);
}