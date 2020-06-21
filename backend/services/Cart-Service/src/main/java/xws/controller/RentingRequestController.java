package xws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import xws.dto.request.CreateRentingRequestRequestDTO;
import xws.dto.request.ManuallyReserveVehicleRequestDTO;
import xws.model.RentingRequest;
import xws.model.RentingRequestVehicle;
import xws.service.RentingRequestService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/rentingRequest")
public class RentingRequestController {

    Logger logger = LoggerFactory.getLogger(RentingRequestController.class);

    @Autowired
    private RentingRequestService rentingRequestService;



    /**
     * Pronalazenje svih rentingRequest za jednog user-a koristeci njegov id
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getAllByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(rentingRequestService.findAllByUserId(id),HttpStatus.ACCEPTED);
    }
    /**
     * Pravljenje pojedinacnog zahveta za svako vozilo iz korpe,koristi se id korpe,pocetni i krajnji datum svih zahveta
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/separate",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> createOnePerVehicle(@RequestBody CreateRentingRequestRequestDTO requestDTO) {
        return new ResponseEntity<>(rentingRequestService.createOneRequestPerVehicle(requestDTO), HttpStatus.CREATED);
    }
    /**
     * Pravljenje bundle zahveta za svako vozilo iz korpe za koje je moguce koristeci id vlasnika vozila,koristi se id korpe,pocetni i krajnji datum svih zahveta
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/bundle",method = RequestMethod.POST,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> createBundlePerOwner(@RequestBody CreateRentingRequestRequestDTO requestDTO) {
        return new ResponseEntity<>(rentingRequestService.createBundlePerOwner(requestDTO), HttpStatus.CREATED);
    }
        /**
     * U slucaju da lice dodje na lice mesta i izvrsi iznajmljivanje vozila,ovom funkcijom se manualno iznajmljuje vozilo i svi ostali zahtevi za to vozilo,kao u bundlovi prelaze u status odbijeni.
     * @param requestDTO
     * @return
     */
    @RequestMapping(value = "/status",method = RequestMethod.PUT,produces = "application/json",consumes = "application/json")
    public ResponseEntity<?> manualyReserveVehicle(@RequestBody ManuallyReserveVehicleRequestDTO requestDTO) {
        return new ResponseEntity<>(rentingRequestService.manualyReserveVehicle(requestDTO),HttpStatus.CREATED);
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {
        List<RentingRequest> rentingRequests = rentingRequestService.findAll();
        for(RentingRequest rentingRequest : rentingRequests) {
            if(rentingRequest.getStatus().equals(RentingRequest.Status.PENDING) && rentingRequest.getCreatedAt().isBefore(LocalDateTime.now().minusDays(1L))) {
                rentingRequest.setStatus(RentingRequest.Status.DECLINED);
                rentingRequestService.save(rentingRequest);
                logger.info("scheduled: stavljam status na declined");
            }else if(rentingRequest.getStatus().equals(RentingRequest.Status.PAID)) {
                for(RentingRequestVehicle rentingRequestVehicle : rentingRequest.getVehicles()){
                    if(rentingRequestVehicle.getEndDate().isBefore(LocalDateTime.now())){
                        rentingRequest.setStatus(RentingRequest.Status.FINISHED);
                        rentingRequestService.save(rentingRequest);
                        logger.info("scheduled: stavljam status na finished");
                        break;
                    }
                }
            }
        }
    }
}
