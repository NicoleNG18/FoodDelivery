package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductService {

private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductEntity> allProducts(ProductCategoryEnum category) {
        return this.productRepository.findAllByCategory(category);
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
