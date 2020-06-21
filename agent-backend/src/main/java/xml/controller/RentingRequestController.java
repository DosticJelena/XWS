package xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xml.dto.request.NewVehicleRequestDTO;
import xml.dto.request.ReportRequestDTO;
import xml.model.RentingRequestVehicle;
import xml.model.Vehicle;
import xml.repository.RentingRequestVehicleRepository;
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
    private VehicleService vehicleService;

    @RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public RentingRequestVehicle report(@RequestBody ReportRequestDTO request) {
        RentingRequestVehicle rrv = rentingRequestVehicleRepository.findOneByVehicleIdAndRentingRequestId(request.getVId(), request.getRId());
        rrv.setDistance(request.getDistance());
        rrv.setAdditionalInfo(request.getAdditionalInfo());
        return rentingRequestVehicleRepository.save(rrv);
    }

    @RequestMapping(value = "/finished", method = RequestMethod.GET, produces = "application/json")
    public List<RentingRequestVehicle> getFinished(@RequestBody Id id) {
        List<Vehicle> vehicles = vehicleService.findAllByOwner_id(id.id);

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
