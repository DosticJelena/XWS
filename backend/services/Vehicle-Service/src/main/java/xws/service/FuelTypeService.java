package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.FuelType;
import xws.repository.FuelTypeRepository;

import java.util.List;

@Service
public class FuelTypeService {

    @Autowired
    FuelTypeRepository fuelTypeRepository;

    public List<FuelType> findAll() {
        return fuelTypeRepository.findAll();
    }

    public FuelType findOneById(Long id) {
        return fuelTypeRepository.findOneById(id);
    }

    public FuelType findOneByFuelType(String fuelType) {
        return fuelTypeRepository.findOneByFuelType(fuelType);
    }

    public FuelType save(FuelType ft) { return fuelTypeRepository.save(ft); }
}
