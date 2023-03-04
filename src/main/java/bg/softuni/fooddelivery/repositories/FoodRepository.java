package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
}
