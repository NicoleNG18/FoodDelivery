package bg.softuni.fooddelivery.domain.entities;


import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductEntity setCategory(ProductCategoryEnum category) {
        this.category = category;
        return this;
    }
}
