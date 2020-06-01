package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.model.Vehicle;
import xws.repository.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public List<Vehicle> search(String location,String brand,
                                String model,String fuel_type,
                                String transmission,String type,
                                double price,double distance,String CDWStatus,
                                int childrenSeats){
        List<Vehicle> allVehicles = vehicleRepository.findByLocation(location);
        return  allVehicles;
    }
    public List<Vehicle> getAll(){
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return  allVehicles;
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findOneById(id);
    }

    public Vehicle save(Vehicle newVehicle) {
        Vehicle v = new Vehicle();
        v.setBrand(newVehicle.getBrand());
        v.setLocation(newVehicle.getLocation());
        v.setModel(newVehicle.getModel());
        v.setFuel_type(newVehicle.getFuel_type());
        v.setTransmission(newVehicle.getTransmission());
        v.setPrice(newVehicle.getPrice());

        v.setAdditionalPricePerKm(newVehicle.getAdditionalPricePerKm());
        v.setCDWStatus(newVehicle.getCDWStatus());
        v.setChildrenSeats(newVehicle.getChildrenSeats());
        v.setDistance(newVehicle.getDistance());
        v.setDistancePerRentStatus(newVehicle.getDistancePerRentStatus());
        v.setDistancePerRent(newVehicle.getDistancePerRent());

        vehicleRepository.save(v);
        return v;
    }

}
