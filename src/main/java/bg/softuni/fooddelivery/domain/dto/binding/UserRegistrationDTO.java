package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.validation.FieldMatch;
import bg.softuni.fooddelivery.validation.UniqueUserEmail;
import bg.softuni.fooddelivery.validation.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@FieldMatch(firstField = "password",
        secondField = "confirmPassword",
        message = "Passwords should match.")
public class UserRegistrationDTO {

    @Size(min =2,max = 15,message = "First name must be between 2 and 15 symbols.")
    private String firstName;

    @Size(min =2,max = 15,message = "Last name must be between 2 and 15 symbols.")
    private String lastName;

    @UniqueUsername(message = "Username should be unique.")
    @Size(min =2,message = "Username must be at least 5 symbols.")
    private String username;

    @NotEmpty(message = "Email should be provided.")
    @UniqueUserEmail(message = "Email should be unique.")
    private String email;

    @Size(min =8,message = "Password must be at least 8 symbols.")
    private String password;

    private String confirmPassword;

    @Positive
    @NotNull(message = "Age should be provided.")
    private Integer age;

    @NotEmpty(message = "Phone number should be provided")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @NotNull
    private GenderEnum gender;

    public UserRegistrationDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegistrationDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegistrationDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegistrationDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserRegistrationDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserRegistrationDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserRegistrationDTO setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
