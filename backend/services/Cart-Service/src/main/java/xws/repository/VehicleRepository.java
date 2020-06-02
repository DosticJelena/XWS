package xws.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.VehicleCart;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<VehicleCart,Long> {

    Optional<VehicleCart> findOneById(Long id);
}
