package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAll();
    List<Vehicle> findByLocation(String location);
    Vehicle findOneById(Long id);

}