package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class ShoppingCartEntity extends BaseEntity {

    @OneToMany(fetch=FetchType.EAGER)
    private List<ProductEntity> products;

    public ShoppingCartEntity() {
        this.products = new ArrayList<>();
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public ShoppingCartEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
}
