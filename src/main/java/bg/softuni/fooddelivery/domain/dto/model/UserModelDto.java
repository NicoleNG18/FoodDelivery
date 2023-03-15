package bg.softuni.fooddelivery.domain.dto.model;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import jakarta.persistence.*;

import java.util.Set;

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

    private Set<OrderEntity> orders;

    private CartModelDto cart;

    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public UserModelDto setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }

    public CartModelDto getCart() {
        return cart;
    }

    public UserModelDto setCart(CartModelDto cart) {
        this.cart = cart;
        return this;
    }

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

    public UserModelDto toModel(UserEntity userEntity) {
        return new UserModelDto();
    }
}
