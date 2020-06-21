package xml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xml.model.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByVehicleId(Long id);
    List<Picture> findAll();
}