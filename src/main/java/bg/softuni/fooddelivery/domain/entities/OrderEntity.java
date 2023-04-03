package bg.softuni.fooddelivery.domain.entities;

import bg.softuni.fooddelivery.domain.enums.Discount;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
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

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column
    private LocalDateTime deliveredOn;

    private String comment;
    @Column(nullable = false)
    private String address;

    @Column
    private String discount;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    public OrderEntity() {
    }

    public String getDiscount() {
        return discount;
    }

    public OrderEntity setDiscount(String discount) {
        this.discount = discount;
        return this;
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

    public OrderEntity setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }
}
