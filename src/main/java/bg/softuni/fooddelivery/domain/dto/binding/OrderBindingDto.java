package bg.softuni.fooddelivery.domain.dto.binding;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderBindingDto {

    private String comment;
    @NotEmpty(message = "Address is required.")
    private String address;
    @NotEmpty(message = "Contact number is required.")
    private String contactNumber;

    public OrderBindingDto() {
    }

    public String getComment() {
        return comment;
    }

    public OrderBindingDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderBindingDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public OrderBindingDto setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }
}
