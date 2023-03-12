package bg.softuni.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ContactFormBindingDto {
    @Size(min = 2, max = 15, message = "Name must be between 2 and 15 symbols.")
    private String name;
    @NotEmpty(message = "Email should be provided.")
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 3, message = "Subject must be at least 3 symbols.")
    private String subject;
    @Size(min = 5, max = 2000, message = "Description must be between 5 and 2000 symbols.")
    private String description;

    public ContactFormBindingDto() {
    }

    public String getName() {
        return name;
    }

    public ContactFormBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactFormBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactFormBindingDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContactFormBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }
}
