package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class ShoppingCartEntity extends BaseEntity {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductEntity> products;

    private long countProducts;

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

    public long getCountProducts() {
        return countProducts;
    }

    public ShoppingCartEntity setCountProducts(long countProducts) {
        this.countProducts = countProducts;
        return this;
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
}
