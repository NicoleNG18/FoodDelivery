package bg.softuni.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class EditProductBindingDto {
    @NotEmpty(message = "Cannot remove description.")
    private String description;
    @Positive(message = "Price must be positive.")
    @NotNull(message = "Price is required.")
    private BigDecimal price;

    public EditProductBindingDto() {
    }

    public String getDescription() {
        return description;
    }

    public EditProductBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public EditProductBindingDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
