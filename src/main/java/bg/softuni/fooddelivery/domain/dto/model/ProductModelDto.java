package bg.softuni.fooddelivery.domain.dto.model;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public class ProductModelDto {

    private String name;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;
    private String description;

    public ProductModelDto() {
    }

    public String getName() {
        return name;
    }

    public ProductModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductModelDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductModelDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductModelDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
