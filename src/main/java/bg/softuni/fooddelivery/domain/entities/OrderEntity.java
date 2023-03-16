package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne
    private UserEntity owner;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToOne
    private CartEntity cart;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column
    private LocalDateTime deliveredOn;

    private String comment;
    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Boolean isDelivered;

    public OrderEntity() {
    }

    public UserEntity getOwner() {
        return owner;
    }

    public OrderEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CartEntity getCart() {
        return cart;
    }

    public OrderEntity setCart(CartEntity shoppingCart) {
        this.cart = shoppingCart;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderEntity setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getDeliveredOn() {
        return deliveredOn;
    }

    public OrderEntity setDeliveredOn(LocalDateTime deliveredOn) {
        this.deliveredOn = deliveredOn;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public OrderEntity setComment(String comments) {
        this.comment = comments;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public OrderEntity setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public OrderEntity setDelivered(Boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
