package bg.softuni.fooddelivery.domain.dto.model;

import java.util.ArrayList;
import java.util.List;

public class CartModelDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public CartModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    private List<ProductModelDto> products;

    public CartModelDto() {
        this.products = new ArrayList<>();
    }

    public List<ProductModelDto> getProducts() {
        return products;
    }

    public CartModelDto setProducts(List<ProductModelDto> products) {
        this.products = products;
        return this;
    }
}
