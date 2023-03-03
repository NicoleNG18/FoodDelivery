package bg.softuni.fooddelivery.domain.entities;


import jakarta.persistence.Column;

import java.math.BigDecimal;

public abstract class ProductEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
