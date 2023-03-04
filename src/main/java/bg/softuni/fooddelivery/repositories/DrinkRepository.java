package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {
}
