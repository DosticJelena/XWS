package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xml.dto.request.NewVehicleRequestDTO;
import xml.dto.request.ReportRequestDTO;
import xml.model.RentingRequest;
import xml.model.RentingRequestVehicle;
import xml.model.Vehicle;
import xml.repository.RentingRequestRepository;
import xml.repository.RentingRequestVehicleRepository;
import xml.repository.VehicleRepository;
import xml.service.RentingRequestService;
import xml.service.VehicleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rentingRequest")
public class RentingRequestController {

    @Autowired
    private RentingRequestVehicleRepository rentingRequestVehicleRepository;

    @Autowired
    private RentingRequestRepository rentingRequestRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(rentingRequestRepository.findByUserId(id), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public RentingRequestVehicle report(@RequestBody ReportRequestDTO request) {
        List<RentingRequestVehicle> lista = rentingRequestVehicleRepository.findAll();

        RentingRequestVehicle rrv = null;

        Vehicle v = vehicleService.findOneById(request.getVid());
        Double d = v.getDistance();
        v.setDistance(d+request.getDistance());
        vehicleRepository.save(v);

        for(RentingRequestVehicle a:lista) {
            System.out.println(request.getVid() + "    " + request.getRid());
            if(a.getId().getVehicle_id() == request.getVid() && a.getId().getRenting_request_id() == request.getRid()) {
                System.out.println("usao");
                rrv = a;
                break;
            }
        }
        System.out.println(rrv.getDistance());
        rrv.setDistance(request.getDistance());
        rrv.setAdditionalInfo(request.getAdditionalInfo());

        return rentingRequestVehicleRepository.save(rrv);
    }

    @RequestMapping(value = "/finished/{id}", method = RequestMethod.GET, produces = "application/json")
    public List<RentingRequestVehicle> getFinished(@PathVariable Long id) {
        List<Vehicle> vehicles = vehicleService.findAllByOwner_id(id);

        ArrayList<List<RentingRequestVehicle>> lista = new ArrayList<List<RentingRequestVehicle>>();
        for(Vehicle v : vehicles) {
            lista.add(rentingRequestVehicleRepository.findAllByVehicleIdAndEndDateIsLessThanAndAdditionalInfoIsNull(v.getId(), LocalDateTime.now()));
        }

        List<RentingRequestVehicle> ret = new ArrayList<>();
        for(int i=0;i<lista.size();i++) {
            List<RentingRequestVehicle> a = lista.get(i);
            for(RentingRequestVehicle rrv : a) {
                ret.add(rrv);
            }
        }

        return ret;
    }

    public static class Id {
        public Long id;
    }
}
