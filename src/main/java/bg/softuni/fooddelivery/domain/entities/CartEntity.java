package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @ManyToMany
    private List<ProductEntity> products;

    public CartEntity() {
        this.products = new ArrayList<>();
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public CartEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
}
