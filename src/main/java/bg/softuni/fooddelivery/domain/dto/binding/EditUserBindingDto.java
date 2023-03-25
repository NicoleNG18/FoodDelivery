package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.validation.UniqueUserEmail;
import bg.softuni.fooddelivery.validation.UniqueUsername;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import static bg.softuni.fooddelivery.constants.ErrorMessages.*;
import static bg.softuni.fooddelivery.constants.ErrorMessages.USERNAME_MINIMUM;

public class EditUserBindingDto {

    @Size(min=1)
    private String username;
    public EditUserBindingDto() {
    }

    public String getUsername() {
        return username;
    }

    public EditUserBindingDto setUsername(String username) {
        this.username = username;
        return this;
    }
}
