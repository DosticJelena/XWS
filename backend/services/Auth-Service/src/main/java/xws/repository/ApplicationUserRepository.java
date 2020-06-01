package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.ApplicationUser;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    List<ApplicationUser> findAll();


}
