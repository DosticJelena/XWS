package xws.service;

import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rx.schedulers.Schedulers;
import xws.dto.request.CreateRentingRequestRequestDTO;
import xws.dto.request.ManuallyReserveVehicleRequestDTO;
import xws.dto.response.RentingRequestResponseDTO;
import xws.feignClients.UserServiceProxy;
import xws.model.Cart;
import xws.model.RentingRequest;
import xws.model.RentingRequestVehicle;
import xws.model.VehicleCart;
import xws.repository.RentingRequestRepository;
import xws.repository.RentingRequestVehicleRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RentingRequestService {
    Logger logger = LoggerFactory.getLogger(RentingRequestService.class);

    @Autowired
    private RentingRequestRepository rentingRequestRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private RentingRequestVehicleRepository rentingRequestVehicleRepository;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private UserServiceProxy userServiceProxy;

    public List<RentingRequest> findAll() { return rentingRequestRepository.findAll(); }
    public List<RentingRequestResponseDTO> findAllByUserId(Long id) {
        List<RentingRequest> rentingRequests = rentingRequestRepository.findByUserId(id);
        List<RentingRequestResponseDTO> response = new ArrayList<>();
        for(RentingRequest r : rentingRequests){
            RentingRequestResponseDTO responseDTO = new RentingRequestResponseDTO();
            responseDTO.setId(r.getId());
            responseDTO.setUserId(r.getUserId());
            responseDTO.setVehicles(r.getVehicles());
            response.add(responseDTO);

        }
        return response;
    }

    public Set<RentingRequestVehicle> findAllVehiclesByRequestId(Long requestId){
        RentingRequest r = rentingRequestRepository.findOneById(requestId);
        return r.getVehicles();
    }

    public ResponseEntity<?> createOneRequestPerVehicle(CreateRentingRequestRequestDTO requestDTO) {
        if(userServiceProxy.getBlocked(requestDTO.getUserId())) {
            return new ResponseEntity<>("Ne mozete da rezervisete jer ste blokirani!", HttpStatus.BAD_REQUEST);
        }
        Cart cart = cartService.findOneByUserId(requestDTO.getUserId());
        List<RentingRequest> response = new ArrayList<>();
        for(VehicleCart v : cart.getVehicles()){
            RentingRequest r = new RentingRequest();
            r.setStatus(RentingRequest.Status.PENDING);
            Long userId = cart.getUserId();
            r.setUserId(userId);
            RentingRequestVehicle rrv = new RentingRequestVehicle();
            rrv.setId(new RentingRequestVehicle.RentingRequestVehicleId(r.getId(),v.getId()));
            rrv.setRentingRequest(r);
            rrv.setVehicle(v);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(requestDTO.getStartDate(), formatter);
            LocalDateTime end = LocalDateTime.parse(requestDTO.getEndDate(), formatter);
            rrv.setStartDate(start);
            rrv.setEndDate(end);
            r.setCreatedAt(LocalDateTime.now());
            rentingRequestRepository.save(r);
            rentingRequestVehicleRepository.save(rrv);
            response.add(r);
        }
        cart.setVehicles(new HashSet<>());
        cartService.save(cart);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }

    public ResponseEntity<?> createBundlePerOwner(CreateRentingRequestRequestDTO requestDTO) {
        if(userServiceProxy.getBlocked(requestDTO.getUserId())) {
            return new ResponseEntity<>("Ne mozete da rezervisete jer ste blokirani!", HttpStatus.BAD_REQUEST);
        }
        Cart cart = cartService.findOneByUserId(requestDTO.getUserId());
        HashMap<Long,ArrayList<VehicleCart>> hashMap = new HashMap<>();
        List<RentingRequest> response = new ArrayList<>();
        for(VehicleCart v : cart.getVehicles()) {
            hashMap.put(v.getOwnerId(),new ArrayList<>());
        }
        for(VehicleCart v : cart.getVehicles()) {
            hashMap.get(v.getOwnerId()).add(v);
        }
        for(Long l : hashMap.keySet()){
            RentingRequest r = new RentingRequest();
            r.setStatus(RentingRequest.Status.PENDING);
            Long userId = cart.getUserId();
            r.setUserId(userId);
            rentingRequestRepository.save(r);
            for(VehicleCart v : hashMap.get(l)) {
                RentingRequestVehicle rrv = new RentingRequestVehicle();
                rrv.setId(new RentingRequestVehicle.RentingRequestVehicleId(r.getId(),v.getId()));
                rrv.setRentingRequest(r);
                rrv.setVehicle(v);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start = LocalDateTime.parse(requestDTO.getStartDate(), formatter);
                LocalDateTime end = LocalDateTime.parse(requestDTO.getEndDate(), formatter);
                rrv.setStartDate(start);
                rrv.setEndDate(end);

                rentingRequestVehicleRepository.save(rrv);

            }
            r.setCreatedAt(LocalDateTime.now());
            rentingRequestRepository.save(r);
            response.add(r);
        }
        cart.setVehicles(new HashSet<>());
        cartService.save(cart);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    public RentingRequest manualyReserveVehicle(ManuallyReserveVehicleRequestDTO requestDTO) {
        VehicleCart v = vehicleService.findOneById(requestDTO.getVehicleId());
        RentingRequest r = new RentingRequest();
        r.setStatus(RentingRequest.Status.PAID);
        rentingRequestRepository.save(r);

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

        rentingRequestVehicleRepository.save(rrv);
        return r;
    }

    public RentingRequest declineRequestAsOwner(Long id){
        RentingRequest r = rentingRequestRepository.getOne(id);
        r.setStatus(RentingRequest.Status.DECLINED);
        return rentingRequestRepository.save(r);
    }

    public RentingRequest reserveVehicleAsOwner(Long id) {
        RentingRequest r = rentingRequestRepository.getOne(id);
        r.setStatus(RentingRequest.Status.PAID);
        rentingRequestRepository.save(r);
        logger.info("Upravo sam sacuvao renting request sa PAID statusom");
        Set<RentingRequestVehicle> rentingRequestVehicles = r.getVehicles();
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        for(RentingRequestVehicle temp : rentingRequestVehicles) {
            logger.info("Radim za vozilo:  " + temp.getVehicle().getId());
            startDate = temp.getStartDate();
            endDate = temp.getEndDate();
            VehicleCart vc = temp.getVehicle();
            List<RentingRequestVehicle> rentingRequestVehicles1 = rentingRequestVehicleRepository.findByVehicleId(vc.getId());
            for(RentingRequestVehicle rentingRequestVehicle : rentingRequestVehicles1){
                logger.info("Radim za renting request: " + rentingRequestVehicle.getRentingRequest().getId() +  " zbog vozila: " + rentingRequestVehicle.getVehicle().getId());
                if(rentingRequestVehicle.getRentingRequest().getStatus().equals(RentingRequest.Status.PENDING) &&
                        (((rentingRequestVehicle.getStartDate().isAfter(startDate) || startDate.isEqual(startDate)) && rentingRequestVehicle.getStartDate().isBefore(endDate)) ||
                                (rentingRequestVehicle.getEndDate().isAfter(startDate) && (rentingRequestVehicle.getEndDate().isBefore(endDate) || rentingRequestVehicle.getEndDate().isEqual(endDate))) ||
                                ((rentingRequestVehicle.getStartDate().isAfter(startDate) || rentingRequestVehicle.getStartDate().isEqual(startDate)) && (rentingRequestVehicle.getEndDate().isBefore(endDate) || rentingRequestVehicle.getEndDate().isEqual(endDate))))){
                    RentingRequest temp123 = rentingRequestVehicle.getRentingRequest();
                    temp123.setStatus(RentingRequest.Status.DECLINED);
                    rentingRequestRepository.save(temp123);
                }
            }
        }
        return r;


    }
    public RentingRequest save(RentingRequest r) {
        return rentingRequestRepository.save(r);
    }
    public List<RentingRequest> getAllByVehicleOwnerId(Long id) {
        List<RentingRequest> rentingRequests = rentingRequestRepository.findAll();
        List<RentingRequest> response = new ArrayList<>();
        for(RentingRequest r : rentingRequests) {
            for(RentingRequestVehicle vc : r.getVehicles()) {
                if(vc.getVehicle().getOwnerId().equals(id) && r.getStatus().equals(RentingRequest.Status.PENDING)){
                    response.add(r);
                    break;
                }
            }
        }
        return response;
    }
    public List<RentingRequest> getAllByUserIdAndRequestStatus(Long id,int status) {
        RentingRequest.Status requestStatus = null;
        switch (status){
            case 0: requestStatus = RentingRequest.Status.PENDING; break;
            case 1: requestStatus = RentingRequest.Status.PAID; break;
            case 2: requestStatus = RentingRequest.Status.DECLINED; break;
            case 3: requestStatus = RentingRequest.Status.FINISHED; break;
            case 4: requestStatus = RentingRequest.Status.CANCLED; break;
        }
        return rentingRequestRepository.findByUserIdAndStatus(id,requestStatus);
    }
    public RentingRequest cancleRentingRequest(Long requestId) {
        RentingRequest rentingRequest = rentingRequestRepository.getOne(requestId);
        rentingRequest.setStatus(RentingRequest.Status.CANCLED);
        return rentingRequestRepository.save(rentingRequest);
    }
}
