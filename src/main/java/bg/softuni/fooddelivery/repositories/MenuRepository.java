package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
