package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;

import java.math.BigDecimal;

public class DrinkViewDto {

    private String name;
    private BigDecimal price;
    private ProductCategoryEnum category;
    private Double quantity;

    public DrinkViewDto() {
    }

    public String getName() {
        return name;
    }

    public DrinkViewDto setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public DrinkViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public DrinkViewDto setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }

    public Double getQuantity() {
        return quantity;
    }

    public DrinkViewDto setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
