package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartEntity,Long> {
}
