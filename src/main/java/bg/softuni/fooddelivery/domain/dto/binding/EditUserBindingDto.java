package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.validation.UniqueUserEmail;
import bg.softuni.fooddelivery.validation.UniqueUsername;
import bg.softuni.fooddelivery.validation.ValidUsername;
import bg.softuni.fooddelivery.validation.contact.ValidPersonName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import static bg.softuni.fooddelivery.constants.ErrorMessages.*;
import static bg.softuni.fooddelivery.constants.ErrorMessages.USERNAME_MINIMUM;

public class EditUserBindingDto {
    @ValidPersonName
    private String firstName;
    @ValidPersonName
    private String lastName;

    public EditUserBindingDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public EditUserBindingDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EditUserBindingDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

}
