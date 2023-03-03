package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name="drinks")
public class DrinkEntity extends ProductEntity{

    @Column(nullable = false)
    private Double quantity;

    public DrinkEntity() {
    }

    public Double getQuantity() {
        return quantity;
    }

    public DrinkEntity setQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }
}
