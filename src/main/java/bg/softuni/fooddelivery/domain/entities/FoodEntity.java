package bg.softuni.fooddelivery.domain.entities;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "foods")
public class FoodEntity extends ProductEntity {

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    public FoodEntity() {
    }

    public String getDescription() {
        return description;
    }

    public FoodEntity setDescription(String description) {
        this.description = description;
        return this;
    }

}
