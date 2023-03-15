package bg.softuni.fooddelivery.domain.dto.model;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.CommentEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;

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
    private List<CommentModelDto> comments;
    private String contactNumber;
    private Boolean isDelivered;

    public OrderModelDto() {
    }

    public UserModelDto getOwner() {
        return owner;
    }

    public OrderModelDto setOwner(UserModelDto owner) {
        this.owner = owner;
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

    public List<CommentModelDto> getComments() {
        return comments;
    }

    public OrderModelDto setComments(List<CommentModelDto> comments) {
        this.comments = comments;
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
