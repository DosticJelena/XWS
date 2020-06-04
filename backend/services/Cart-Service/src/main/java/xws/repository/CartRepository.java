package xws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Optional<Cart> findOneById(Long id);
    Optional<Cart> findOneByUserId(Long id);
}
