package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.view.DrinkViewDto;
import bg.softuni.fooddelivery.domain.dto.view.FoodViewDto;
import bg.softuni.fooddelivery.domain.entities.DrinkEntity;
import bg.softuni.fooddelivery.domain.entities.FoodEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.DrinkRepository;
import bg.softuni.fooddelivery.repositories.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MenuService {

    private final FoodRepository foodRepository;
    private final DrinkRepository drinkRepository;

    private final ModelMapper modelMapper;

    public MenuService(FoodRepository foodRepository,
                       DrinkRepository drinkRepository,
                       ModelMapper modelMapper) {
        this.foodRepository = foodRepository;
        this.drinkRepository = drinkRepository;
        this.modelMapper = modelMapper;
    }

    public List<FoodEntity> allFoodsByCategory(ProductCategoryEnum category) {
        return this.foodRepository.findAllByCategory(category);
    }

    public List<DrinkEntity> allDrinks() {
        return this.drinkRepository.findAll();
    }
}

//    public Page<FoodViewDto> allFoodsByCategory(ProductCategoryEnum category,
//                                                   Pageable pageable){
//       final List<FoodViewDto> foods = this.foodRepository.findAllByCategory(pageable, category)
//                .stream().map(f -> this.modelMapper.map(f, FoodViewDto.class)).toList();
//
//       return new PageImpl<>(foods);
//
//    }
//
//    public Page<DrinkViewDto> allDrinks(Pageable pageable){
//
//        final List<DrinkViewDto> drinks = this.drinkRepository.findAll(pageable)
//                .stream().map(d -> this.modelMapper.map(d, DrinkViewDto.class)).toList();
//
//        return new PageImpl<>(drinks);
//    }

//}
