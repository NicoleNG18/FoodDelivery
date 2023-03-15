package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
    private LocalDateTime deliveredOn;

    @OneToMany
    private List<CommentEntity> comments;

    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private Boolean isDelivered;

    public OrderEntity() {
        this.comments=new ArrayList<>();
    }

    public UserEntity getOwner() {
        return owner;
    }

    public OrderEntity setOwner(UserEntity owner) {
        this.owner = owner;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CartEntity getCart(){
        return cart;
    }

    public OrderEntity setCart(CartEntity shoppingCart){
        this.cart =shoppingCart;
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

    public List<CommentEntity> getComments() {
        return comments;
    }

    public OrderEntity setComments(List<CommentEntity> comments) {
        this.comments = comments;
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
