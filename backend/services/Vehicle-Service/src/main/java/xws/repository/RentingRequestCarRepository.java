package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.RentingRequestCar;

import java.util.List;
import java.util.Optional;

public interface RentingRequestCarRepository extends JpaRepository<RentingRequestCar, Long> {

    List<RentingRequestCar> findAll();
    List<RentingRequestCar> findAllById(Long id);

}