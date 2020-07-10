package xml.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xml.dto.request.NewVehicleRequestDTO;
import xml.model.Picture;
import xml.model.RentingRequestCar;
import xml.model.Vehicle;
import xml.model.VehicleCart;
import xml.repository.RentingRequestCarRepository;
import xml.repository.VehicleRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
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

    public Vehicle changeLocation(Long id) {
        Vehicle v = vehicleRepository.findOneById(id);
        String loc = v.getGps();
        String loc1 = loc.split(",")[0];
        String loc2 = loc.split(",")[1];
        double loc1F = Float.parseFloat(loc1);
        double loc2F = Float.parseFloat(loc2);
        double min = -0.01;
        double max = 0.01;
        double random_double = Math.random() * (max - min + 1) + min;
        loc1F += random_double;
        loc2F += random_double;
        loc = String.valueOf(loc1F) + "," + String.valueOf(loc2F);
        v.setGps(loc);
        vehicleRepository.save(v);
        return v;
    }

    public Vehicle findOneById(Long id) {
        return this.vehicleRepository.findOneById(id);
    }

    public List<Vehicle> findAllByOwner_id(Long id) { return this.vehicleRepository.findAllByOwner(id); }

    public List<Vehicle> search(String location, String startDate, String endDate, String brand,
                                String model, String fuel_type,
                                String transmission, String type,
                                double minPrice, double maxPrice, double distance, String CDWStatus,
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
        v.setOwner(newVehicle.getOwner_id());
        v.setBrand(newVehicle.getBrand());
        v.setLocation(newVehicle.getLocation());
        v.setModel(newVehicle.getModel());
        v.setFuel_type(newVehicle.getFuel_type());
        v.setTransmission(newVehicle.getTransmission());
        v.setPrice(newVehicle.getPrice());
        v.setVehicle_type(newVehicle.getVehicle_type());
        v.setTrack(1);
        v.setGps("45.858075,19.113003");

        v.setAdditionalPricePerKm(newVehicle.getAdditionalPricePerKm());
        v.setCDWStatus(newVehicle.getCDWStatus());
        v.setChildrenSeats(newVehicle.getChildrenSeats());
        v.setDistance(newVehicle.getDistance());
        v.setDistancePerRentStatus(newVehicle.getDistancePerRentStatus());
        v.setDistancePerRent(newVehicle.getDistancePerRent());

        for (String pictureUrl: newVehicle.getPictures()) {
            Picture p = new Picture();
           // p.setVehicle(v); // suvisno...error?
            p.setUrl(pictureUrl);
            v.getPictures().add(p);
        }

        return vehicleRepository.save(v);

    }

}
