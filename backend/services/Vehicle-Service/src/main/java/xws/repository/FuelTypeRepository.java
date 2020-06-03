package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xws.model.FuelType;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    List<FuelType> findAll();
    FuelType findOneById(Long id);
    FuelType findOneByFuelType(String fuelType);
    FuelType save(FuelType ft);

    @Modifying
    @Transactional
    @Query("update FuelType ft set ft.fuelType = :fuelType where ft.id = :id")
    int updateFuelType(@Param("fuelType") String fuelType, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update FuelType ft set ft.status = 1 where ft.id = :id")
    int deleteFuelType(@Param("id") Long id);
}
