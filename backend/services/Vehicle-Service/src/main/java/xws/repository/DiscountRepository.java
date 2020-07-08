package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.Discount;
import xws.model.FuelType;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    List<Discount> findAll();
    Discount findOneById(Long id);
}
