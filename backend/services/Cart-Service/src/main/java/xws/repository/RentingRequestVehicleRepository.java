package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.RentingRequestVehicle;

public interface RentingRequestVehicleRepository extends JpaRepository<RentingRequestVehicle, RentingRequestVehicle.RentingRequestVehicleId> {

}
