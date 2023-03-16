package bg.softuni.fooddelivery.domain.dto.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderModelDto {
    private Long id;

    public Long getId() {
        return id;
    }

    public OrderModelDto setId(Long id) {
        this.id = id;
        return this;
    }
    private UserModelDto owner;
    private BigDecimal price;
    private CartModelDto cart;
    private LocalDateTime createdOn;
    private LocalDateTime deliveredOn;
    private String comment;
    private String contactNumber;
    private Boolean isDelivered;

    private String address;

    public OrderModelDto() {
    }

    public UserModelDto getOwner() {
        return owner;
    }

    public OrderModelDto setOwner(UserModelDto owner) {
        this.owner = owner;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderModelDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderModelDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CartModelDto getCart() {
        return cart;
    }

    public OrderModelDto setCart(CartModelDto cart) {
        this.cart = cart;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderModelDto setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getDeliveredOn() {
        return deliveredOn;
    }

    public OrderModelDto setDeliveredOn(LocalDateTime deliveredOn) {
        this.deliveredOn = deliveredOn;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public OrderModelDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public OrderModelDto setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public OrderModelDto setDelivered(Boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
