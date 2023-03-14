package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.DrinkEntity;
import bg.softuni.fooddelivery.domain.entities.FoodEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.DrinkRepository;
import bg.softuni.fooddelivery.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    private final FoodRepository foodRepository;
    private final DrinkRepository drinkRepository;

    public MenuService(FoodRepository foodRepository,
                       DrinkRepository drinkRepository) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
    }


    public List<FoodEntity> allFoodsByCategory(ProductCategoryEnum category) {
        return this.foodRepository.findAllByCategory(category);
    }

    public List<DrinkEntity> allDrinks() {
        return this.drinkRepository.findAll();
    }
}
