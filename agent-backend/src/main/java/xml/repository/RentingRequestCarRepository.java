package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.RentingRequestCar;

import java.util.List;

public interface RentingRequestCarRepository extends JpaRepository<RentingRequestCar, Long> {

    List<RentingRequestCar> findAll();
    List<RentingRequestCar> findAllById(Long id);

}
