package xml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.dto.request.ManuallyReserveVehicleRequestDTO;
import xml.model.RentingRequest;
import xml.model.RentingRequestVehicle;
import xml.model.Vehicle;
import xml.model.VehicleCart;
import xml.repository.RentingRequestRepository;
import xml.repository.RentingRequestVehicleRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentingRequestService {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RentingRequestVehicleRepository rentingRequestVehicleRepository;

    @Autowired
    private RentingRequestRepository rentingRequestRepository;

    public RentingRequest manuallyReserveVehicle(ManuallyReserveVehicleRequestDTO requestDTO) {
        Vehicle vc = vehicleService.findOneById(requestDTO.getVehicleId());
        VehicleCart v = new VehicleCart();
        v.setOwnerId(vc.getOwner());
        RentingRequest r = new RentingRequest();
        r.setStatus(RentingRequest.Status.PAID);
        RentingRequestVehicle rrv = new RentingRequestVehicle();
        rrv.setId(new RentingRequestVehicle.RentingRequestVehicleId(r.getId(),v.getId()));
        rrv.setRentingRequest(r);
        rrv.setVehicle(v);
        LocalDateTime startDate = LocalDateTime.of(2020,1,10,0,0);
        LocalDateTime endDate = LocalDateTime.of(2020,1,20,0,0);
        rrv.setStartDate(startDate);
        rrv.setEndDate(endDate);


        List<RentingRequestVehicle> rentingRequestVehicles = rentingRequestVehicleRepository.findByVehicleId(v.getId());
        for(RentingRequestVehicle rentingRequestVehicle : rentingRequestVehicles){
            if(rentingRequestVehicle.getRentingRequest().getStatus().equals(RentingRequest.Status.PENDING) &&
                    (((rentingRequestVehicle.getStartDate().isAfter(startDate) || startDate.isEqual(startDate)) && rentingRequestVehicle.getStartDate().isBefore(endDate)) ||
                            (rentingRequestVehicle.getEndDate().isAfter(startDate) && (rentingRequestVehicle.getEndDate().isBefore(endDate) || rentingRequestVehicle.getEndDate().isEqual(endDate))) ||
                            ((rentingRequestVehicle.getStartDate().isAfter(startDate) || rentingRequestVehicle.getStartDate().isEqual(startDate)) && (rentingRequestVehicle.getEndDate().isBefore(endDate) || rentingRequestVehicle.getEndDate().isEqual(endDate))))){
                rentingRequestVehicle.getRentingRequest().setStatus(RentingRequest.Status.DECLINED);
                rentingRequestVehicleRepository.save(rentingRequestVehicle);
            }
        }
        rentingRequestRepository.save(r);
        rentingRequestVehicleRepository.save(rrv);


        return r;


    }
}
