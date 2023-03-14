package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;

import java.math.BigDecimal;

public class FoodViewDto {

    private String name;
    private BigDecimal price;
    private ProductCategoryEnum category;
    private String description;

    public FoodViewDto() {
    }

    public String getName() {
        return name;
    }

    public FoodViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public FoodViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public FoodViewDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public FoodViewDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
