package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.FuelType;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    List<FuelType> findAll();
    FuelType findOneById(Long id);
    FuelType findOneByFuelType(String fuelType);
    FuelType save(FuelType ft);
}
