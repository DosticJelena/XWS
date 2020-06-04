package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.RentingRequest;

import java.util.List;

public interface RentingRequestRepository extends JpaRepository<Long, RentingRequest> {
    public List<RentingRequest> findAllByUserId(Long id);
}
