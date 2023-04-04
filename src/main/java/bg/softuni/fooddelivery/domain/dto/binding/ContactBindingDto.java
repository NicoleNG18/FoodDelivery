package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.validation.common.ValidEmail;
import bg.softuni.fooddelivery.validation.common.ValidPersonName;
import jakarta.validation.constraints.Size;

import static bg.softuni.fooddelivery.constants.ValidationErrorMessages.*;

public class ContactBindingDto {
    @ValidPersonName
    private String name;
    @ValidEmail
    private String email;
    @Size(min = 3, message = SUBJECT_MINIMUM)
    private String subject;
    @Size(min = 5, max = 2000, message = DESCRIPTION_BETWEEN)
    private String description;

    public ContactBindingDto() {
    }

    public String getName() {
        return name;
    }

    public ContactBindingDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactBindingDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactBindingDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContactBindingDto setDescription(String description) {
        this.description = description;
        return this;
    }

}
