package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.RentingRequest;

import java.util.List;

public interface RentingRequestRepository extends JpaRepository<RentingRequest,Long> {
    List<RentingRequest> findByUserId(Long id);
}