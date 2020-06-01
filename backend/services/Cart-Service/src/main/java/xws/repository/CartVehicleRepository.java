package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.CartVehicle;

public interface CartVehicleRepository extends JpaRepository<CartVehicle,Long> {
}
