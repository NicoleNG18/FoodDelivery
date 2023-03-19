package bg.softuni.fooddelivery.domain.dto.binding;

public class EditUserBindingDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Integer age;
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
