package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.RentingRequest;

import java.util.List;

public interface RentingRequestRepository extends JpaRepository<RentingRequest,Long> {

    List<RentingRequest> findByUserId(Long id);
    List<RentingRequest> findByUserIdAndStatus(Long id,RentingRequest.Status status);
}

