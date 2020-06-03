package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.Picture;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    List<Picture> findAllByVehicleId(Long id);
}
