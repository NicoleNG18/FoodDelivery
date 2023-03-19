package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;

import java.util.ArrayList;
import java.util.List;

public class UserViewDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private Integer age;
    private GenderEnum gender;

    private List<UserRoleEntity> roles;

    public UserViewDto() {
    }

    public UserViewDto setId(Long id) {
        this.id = id;
        return this;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    public UserViewDto setRoles(List<UserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewDto setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserViewDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserViewDto setAge(Integer age) {
        this.age = age;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserViewDto setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public Long getId() {
        return id;
    }

    public List<String> getRolesNames(){
        List<String> names=new ArrayList<>();
        roles.forEach(r->names.add(r.getRole().name()));
        return names;
    }

    public boolean hasRoleWorker(){
        return getRolesNames().contains("WORKER");
    }
}
