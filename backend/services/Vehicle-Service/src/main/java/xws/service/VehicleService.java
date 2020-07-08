package xws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.dto.request.NewVehicleRequestDTO;
import xws.dto.response.VehicleForCartDTOResponse;
import xws.model.Picture;
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
                                double minPrice,double maxPrice,double distanceFrom,double distanceTo,String CDWStatus,
                                int childrenSeats){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);

        List<Vehicle> allVehicles = vehicleRepository.findByLocation(location);

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
        List<Vehicle> advancedSearchVehicles = new ArrayList<Vehicle>();
        for (Vehicle v : allVehicles){
            if(!v.getBrand().equals(brand)&&!brand.equals("Brand"))continue;
            if(!v.getVehicle_type().equals(type)&&!type.equals("Vehicle type"))continue;
            if(!v.getModel().equals(model)&&!model.equals("Model"))continue;
            if(!v.getFuel_type().equals(fuel_type)&&!fuel_type.equals("Fuel type"))continue;
            if(!v.getTransmission().equals(transmission)&&!transmission.equals("Transmission"))continue;
            if(!v.getCDWStatus().equals(CDWStatus)&&!CDWStatus.equals("CDW"))continue;
            if(!(v.getPrice()<maxPrice)&& !(maxPrice ==0))continue;
            if(!(v.getPrice()>minPrice)&& !(minPrice ==0))continue;
            if(!(v.getDistance()<distanceFrom)&& !(distanceFrom ==0))continue;
            if(!(v.getDistance()>distanceTo)&& !(distanceTo ==0))continue;
            if(!(v.getChildrenSeats()==childrenSeats)&& !(childrenSeats ==0))continue;
            advancedSearchVehicles.add(v);

        }
        return  advancedSearchVehicles;
    }
    public List<Vehicle> getAll(){
        List<Vehicle> allVehicles = vehicleRepository.findAll();
        return  allVehicles;
    }

    public List<Vehicle> findAllByIds(ArrayList<Long> ids) {
        return vehicleRepository.findAllById(ids);
    }

    public Vehicle getById(Long id) {
        return vehicleRepository.findOneById(id);
    }

    public List<Vehicle> getByOwnerId(Long id) {
        return vehicleRepository.findByOwner(id);
    }

    public Vehicle save(NewVehicleRequestDTO newVehicle) {
        Vehicle v = new Vehicle();
        v.setOwner(newVehicle.getOwner_id());
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

        for (String pictureUrl: newVehicle.getPictures()) {
            Picture p = new Picture();
            p.setVehicle(v); // suvisno...error?
            p.setUrl(pictureUrl);
            v.getPictures().add(p);
        }

        return vehicleRepository.save(v);

    }
    public Vehicle save(Vehicle v) {
        return vehicleRepository.save(v);
    }

}
