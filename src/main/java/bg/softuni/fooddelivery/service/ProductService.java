package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.EditProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.binding.AddProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.view.ProductViewDto;
import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import bg.softuni.fooddelivery.exception.WrongCategoryException;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static bg.softuni.fooddelivery.constants.Messages.PRODUCT;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductViewDto> getAllProductsByCategory(ProductCategoryEnum category) {
        return this.productRepository
                .findAllByCategory(category)
                .stream()
                .map(this::mapToViewDto)
                .collect(Collectors.toList());
    }

    private ProductViewDto mapToViewDto(ProductEntity productEntity) {
        return this.modelMapper.map(productEntity, ProductViewDto.class);
    }

    public String getCategoryName(Long id) {
        return this.productRepository
                .findProductEntityById(id)
                .getCategory()
                .name();
    }

    public void saveProduct(AddProductBindingDto productDto) {

        ProductEntity productToSave = createProduct(productDto);

        this.productRepository.saveAndFlush(productToSave);
    }

    private static ProductEntity createProduct(AddProductBindingDto productDto) {

        ProductEntity productToSave = new ProductEntity();

        productToSave
                .setName(productDto.getName())
                .setCategory(productDto.getCategory())
                .setDescription(productDto.getDescription())
                .setPrice(productDto.getPrice());

        return productToSave;
    }

    public ProductViewDto getProductById(Long productId) {

        ProductEntity productEntity = this.productRepository
                .findById(productId)
                .orElseThrow(() -> new NotFoundObjectException(productId, PRODUCT));

        return this.modelMapper.map(productEntity, ProductViewDto.class);
    }

    public void editProduct(Long productId,
                            EditProductBindingDto editedProductDto) {

        ProductEntity productEntityById = this.productRepository
                .findProductEntityById(productId);

        productEntityById
                .setDescription(editedProductDto.getDescription())
                .setPrice(editedProductDto.getPrice());

        this.productRepository.saveAndFlush(productEntityById);

    }

    public void deleteProduct(Long productId) {
        this.productRepository.deleteById(productId);
    }

    public ProductCategoryEnum findCategory(String category) {

        for (ProductCategoryEnum categoryEnum : ProductCategoryEnum.values()) {
            if (categoryEnum.name().equals(category)) {
                return categoryEnum;
            }
        }

        throw new WrongCategoryException(category);
    }

}
