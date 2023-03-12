package bg.softuni.fooddelivery.domain.dto;

import java.time.LocalDateTime;

public class ContactModelDto {
    private String name;
    private String email;
    private String subject;
    private String description;

    private LocalDateTime createdOn;

    public ContactModelDto() {
    }

    public String getName() {
        return name;
    }

    public ContactModelDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ContactModelDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public ContactModelDto setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContactModelDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ContactModelDto setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
