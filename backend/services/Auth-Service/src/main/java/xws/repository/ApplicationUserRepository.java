package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import xws.model.ApplicationUser;

import java.util.List;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    List<ApplicationUser> findAll();
    ApplicationUser findOneById(Long id);
    ApplicationUser findOneByUsername(String username);

    @Modifying
    @Transactional
    @Query("update ApplicationUser au set au.status = :status where au.id = :id")
    int updateUserStatus(@Param("status") ApplicationUser.Status status, @Param("id") Long id);

    ApplicationUser save(ApplicationUser user);
}
