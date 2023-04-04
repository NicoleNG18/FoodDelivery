package bg.softuni.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

import static bg.softuni.fooddelivery.constants.ValidationErrorMessages.*;

public class EditProductBindingDto {
    @NotEmpty(message = DESCRIPTION_NOT_EMPTY)
    private String description;
    @Positive(message = POSITIVE_PRICE)
    @NotNull(message = PRICE_REQUIRED)
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
