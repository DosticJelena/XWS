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

import javax.websocket.server.PathParam;
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

    @RequestMapping(value= "/{id}/{status}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getallByUserIdAndRequestStatus(@PathVariable("id") Long id,@PathVariable("status") int status) {
        return new ResponseEntity<>(rentingRequestService.getAllByUserIdAndRequestStatus(id,status),HttpStatus.ACCEPTED);
    }

    /**
     * Pronalazenje svih RentingRequest za jednog ownerna
     * @param id
     * @return
     */
    @RequestMapping(value = "owner/{id}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getAllByVehicleOwnerId(@PathVariable Long id){
        return new ResponseEntity<>(rentingRequestService.getAllByVehicleOwnerId(id),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "vehicles/{requestId}",method = RequestMethod.GET,produces = "application/json")
    public ResponseEntity<?> getAllVehiclesByOwnerIdAndRequestId(@PathVariable Long requestId){
        return new ResponseEntity<>(rentingRequestService.findAllVehiclesByRequestId(requestId),HttpStatus.ACCEPTED);
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

    @RequestMapping(value = "/cancle/{id}",method = RequestMethod.PUT,produces = "application/json")
    public ResponseEntity<?> cancelRequest(@PathVariable Long id) {
        return new ResponseEntity<>(rentingRequestService.cancleRentingRequest(id),HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "reserver/{id}",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<?> reserveAsOwner(@PathVariable Long id) {
        RentingRequest ret = rentingRequestService.reserveVehicleAsOwner(id);
        return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "decline/{id}",method = RequestMethod.PUT,produces = "application/json")
    public ResponseEntity<?> declineAsOwner(@PathVariable Long id) {
        return new ResponseEntity<>(rentingRequestService.declineRequestAsOwner(id),HttpStatus.ACCEPTED);
    }

    @Scheduled(fixedRate = 50000)
    public void scheduleFixedRateTask() {
        logger.info("Izvrsavam scheduled task da stavlja statuse na declined i finished");
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
