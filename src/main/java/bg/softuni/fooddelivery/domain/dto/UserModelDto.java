package bg.softuni.fooddelivery.domain.dto;

import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import jakarta.persistence.*;

public class UserModelDto {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private Integer age;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public UserModelDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModelDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModelDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModelDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModelDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModelDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserModelDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserModelDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserModelDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }
}
