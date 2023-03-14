package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.DrinkEntity;
import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {

}
