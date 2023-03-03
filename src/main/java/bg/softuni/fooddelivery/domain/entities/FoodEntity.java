package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "foods")
public class FoodEntity extends ProductEntity {

    @Column(nullable = false)
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
