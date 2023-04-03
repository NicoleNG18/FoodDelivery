package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.domain.enums.Discount;
import bg.softuni.fooddelivery.validation.DiscountMatch;
import bg.softuni.fooddelivery.validation.ValidPhoneNumber;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import static bg.softuni.fooddelivery.constants.ErrorMessages.ADDRESS_REQUIRED;
import static bg.softuni.fooddelivery.constants.ErrorMessages.CONTACT_NUMBER_REQUIRED;

public class OrderBindingDto {

    private String comment;
    @NotEmpty(message = ADDRESS_REQUIRED)
    private String address;

    @DiscountMatch
    private String discount;
    @ValidPhoneNumber
    private String contactNumber;


    public OrderBindingDto() {
    }


    public String getDiscount() {
        return discount;
    }

    public OrderBindingDto setDiscount(String discount) {
        this.discount = discount;
        return this;
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
