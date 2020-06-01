package xws.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.Cart;
import xws.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Optional<Vehicle> findOneById(Long id);
}
