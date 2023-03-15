package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.view.ProductViewDto;
import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductViewDto> allProducts(ProductCategoryEnum category) {
        return this.productRepository
                .findAllByCategory(category)
                .stream()
                .map(this::mapToViewDto)
                .collect(Collectors.toList());
    }

    private ProductViewDto mapToViewDto(ProductEntity productEntity) {
        return this.modelMapper.map(productEntity,ProductViewDto.class);
    }

    public String getCategoryName(Long id) {
        return this.productRepository.findProductEntityById(id).getCategory().name();
    }

}
