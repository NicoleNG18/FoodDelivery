package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.validation.common.ValidPersonName;

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
