package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;

import java.math.BigDecimal;

public class ProductViewDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public ProductViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductViewDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    private String name;

    private ProductCategoryEnum category;

    private BigDecimal price;
    private String description;

    public ProductViewDto() {
    }

    public String getName() {
        return name;
    }

    public ProductViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductViewDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
