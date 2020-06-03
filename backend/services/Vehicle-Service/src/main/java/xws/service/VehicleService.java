package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.request.NewVehicleRequestDTO;
import xws.model.RentingRequestCar;
import xws.model.Vehicle;
import xws.repository.RentingRequestCarRepository;
import xws.repository.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    RentingRequestCarRepository rentingRequestCarRepository;

    public List<Vehicle> search(String location,String startDate,String endDate, String brand,
                                String model,String fuel_type,
                                String transmission,String type,
                                double minPrice,double maxPrice,double distance,String CDWStatus,
                                int childrenSeats){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        List<Vehicle> allVehicles = vehicleRepository.findByLocation(location);
        /*List<Vehicle> advancedSearchVehicles = new ArrayList<Vehicle>();
        for (Vehicle v : allVehicles){
            if(!v.getBrand().equals(brand)&&!brand.equals(""))continue;
            if(!v.getModel().equals(model)&&!model.equals(""))continue;
            if(!v.getFuel_type().equals(fuel_type)&&!fuel_type.equals(""))continue;
            if(!v.getTransmission().equals(transmission)&&!transmission.equals(""))continue;
            if(!v.getType().equals(type)&&!type.equals(""))continue;
            if(!v.getCDWStatus().equals(CDWStatus)&&!CDWStatus.equals(""))continue;

        }*/
        List<Vehicle> ret = new ArrayList<Vehicle>();
        for (Vehicle v : allVehicles){
            List<RentingRequestCar> requests = rentingRequestCarRepository.findAllById(v.getId());
            boolean forAdding = true;
            for(RentingRequestCar r : requests) {
                if(r.getStartDate().isBefore(end) && r.getEndDate().isAfter(end)) {
                    forAdding = false;
                    break;
                }else if(r.getStartDate().isBefore(start) && r.getEndDate().isAfter(start)) {
                    forAdding = false;
                    break;
                }
            }
            if(forAdding){
                ret.add(v);
            }
        }
        return  ret;
    }
    public List<Vehicle> getAll(){
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return  allVehicles;
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findOneById(id);
    }

    public Vehicle save(NewVehicleRequestDTO newVehicle) {
        Vehicle v = new Vehicle();
        v.setUser_id(newVehicle.getUser_id());
        v.setBrand(newVehicle.getBrand());
        v.setLocation(newVehicle.getLocation());
        v.setModel(newVehicle.getModel());
        v.setFuel_type(newVehicle.getFuel_type());
        v.setTransmission(newVehicle.getTransmission());
        v.setPrice(newVehicle.getPrice());
        v.setVehicle_type(newVehicle.getVehicle_type());

        v.setAdditionalPricePerKm(newVehicle.getAdditionalPricePerKm());
        v.setCDWStatus(newVehicle.getCDWStatus());
        v.setChildrenSeats(newVehicle.getChildrenSeats());
        v.setDistance(newVehicle.getDistance());
        v.setDistancePerRentStatus(newVehicle.getDistancePerRentStatus());
        v.setDistancePerRent(newVehicle.getDistancePerRent());

        return vehicleRepository.save(v);

    }

}
