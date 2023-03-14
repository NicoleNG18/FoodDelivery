package bg.softuni.fooddelivery.domain.entities;


import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import java.math.BigDecimal;
@MappedSuperclass
public abstract class ProductEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategoryEnum category;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }

}
