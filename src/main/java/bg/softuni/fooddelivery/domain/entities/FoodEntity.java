package bg.softuni.fooddelivery.domain.entities;

import bg.softuni.fooddelivery.domain.enums.FoodCategoryEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "foods")
public class FoodEntity extends ProductEntity {

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodCategoryEnum category;

    public FoodEntity() {
    }

    public String getDescription() {
        return description;
    }

    public FoodEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public FoodCategoryEnum getCategory() {
        return category;
    }

    public FoodEntity setCategory(FoodCategoryEnum category) {
        this.category = category;
        return this;
    }
}
