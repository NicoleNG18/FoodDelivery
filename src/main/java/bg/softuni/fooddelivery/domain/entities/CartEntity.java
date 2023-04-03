package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity extends BaseEntity {

    @ManyToMany
    private List<ProductEntity> products;

    private Long countProducts;

    private BigDecimal productsSum;

    public CartEntity() {
        this.productsSum = BigDecimal.ZERO;
        this.products = new ArrayList<>();
        this.countProducts = 0L;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public CartEntity setProducts(List<ProductEntity> products) {
        this.products = products;
        return this;
    }

    public BigDecimal getProductsSum() {
        return productsSum;
    }

    public void setProductsSum(BigDecimal productsSum) {
        this.productsSum = productsSum;
    }

    public Long getCountProducts() {
        return countProducts;
    }

    public void setCountProducts(Long countProducts) {
        this.countProducts = countProducts;
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }

    public void increaseProductsSum(BigDecimal productPrice) {

        BigDecimal sum = this.getProductsSum();

        this.setProductsSum(sum.add(productPrice));
        this.countProducts++;
    }

    public void decreaseProductsSum(BigDecimal productPrice) {

        BigDecimal sum = this.getProductsSum();

        this.setProductsSum(sum.subtract(productPrice));
        this.countProducts--;
    }

}
