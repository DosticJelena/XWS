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

@RestController
@RequestMapping(value = "/rentingRequest")
public class RentingRequestController {

    @Autowired
    private RentingRequestVehicleRepository rentingRequestVehicleRepository;

    @RequestMapping(value = "/report", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public RentingRequestVehicle report(@RequestBody ReportRequestDTO request) {
        RentingRequestVehicle rrv = rentingRequestVehicleRepository.findOneByVehicleIdAndRentingRequestId(request.getVId(), request.getRId());
        rrv.setDistance(request.getDistance());
        rrv.setAdditionalInfo(request.getAdditionalInfo());
        return rentingRequestVehicleRepository.save(rrv);
    }
}
