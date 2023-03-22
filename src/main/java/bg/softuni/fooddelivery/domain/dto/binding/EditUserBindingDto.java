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

    @Size(min =2,max = 15,message = FIRST_NAME_BETWEEN)
    private String firstName;
    @Size(min =2,max = 15,message = LAST_NAME_BETWEEN)
    private String lastName;
    @UniqueUsername(message = UNIQUE_USERNAME)
    @Size(min =2,message = USERNAME_MINIMUM)
    private String username;
    @NotEmpty(message = EMAIL_REQUIRED)
    @UniqueUserEmail(message = EMAIL_UNIQUE)
    private String email;
    @Positive
    @NotNull(message = AGE_PROVIDED)
    private Integer age;
    @NotEmpty(message = PHONE_NUMBER_PROVIDED)
    private String phoneNumber;

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

    public String getUsername() {
        return username;
    }

    public EditUserBindingDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditUserBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EditUserBindingDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EditUserBindingDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
