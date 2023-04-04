package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.validation.common.ValidEmail;
import bg.softuni.fooddelivery.validation.common.ValidPersonName;
import bg.softuni.fooddelivery.validation.common.ValidPhoneNumber;
import bg.softuni.fooddelivery.validation.user.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import static bg.softuni.fooddelivery.constants.ErrorMessages.*;
import static bg.softuni.fooddelivery.constants.Messages.*;

@FieldMatch(firstField = PASSWORD,
        secondField = CONFIRM_PASSWORD,
        message = MATCHING_PASSWORDS)
public class UserRegistrationBindingDto {

    @ValidPersonName
    private String firstName;

    @ValidPersonName
    private String lastName;

    @UniqueUsername(message = UNIQUE_USERNAME)
    @ValidUsername
    private String username;

    @ValidEmail
    @UniqueUserEmail(message = EMAIL_UNIQUE)
    private String email;

    @ValidPassword
    private String password;

    private String confirmPassword;

    @Positive
    @NotNull(message = AGE_PROVIDED)
    private Integer age;

    @ValidPhoneNumber
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;

    public UserRegistrationBindingDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationBindingDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationBindingDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationBindingDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationBindingDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationBindingDto setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegistrationBindingDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationBindingDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserRegistrationBindingDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
