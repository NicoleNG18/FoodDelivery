package bg.softuni.fooddelivery.domain.dto.binding;

import bg.softuni.fooddelivery.validation.order.DiscountMatch;
import bg.softuni.fooddelivery.validation.common.ValidPhoneNumber;
import bg.softuni.fooddelivery.validation.order.ValidAddress;

public class OrderBindingDto {

    private String comment;
    @ValidAddress
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
