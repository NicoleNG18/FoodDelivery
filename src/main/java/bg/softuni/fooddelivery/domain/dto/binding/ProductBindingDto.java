package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class ProductBindingDto {
    @NotEmpty(message = "Product name is required.")
    private String name;
    @Positive(message = "Price must be positive.")
    @NotNull(message = "Price is required.")
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    @NotNull
    private ProductCategoryEnum category;
    @NotEmpty(message = "Description is required.")
    private String description;

    public ProductBindingDto() {
    }

    public String getName() {
        return name;
    }

    public ProductBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductBindingDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductBindingDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
