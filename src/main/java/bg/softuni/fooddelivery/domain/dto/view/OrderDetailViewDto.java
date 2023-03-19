package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.entities.CartEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderDetailViewDto {

    private Long id;

    private BigDecimal price;

    private LocalDateTime createdOn;

    private LocalDateTime deliveredOn;

    private String address;

    private String contactNumber;

    private CartEntity cart;

    private Boolean isDelivered;

    private String client;


    public OrderDetailViewDto() {
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderDetailViewDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getClient() {
        return client;
    }

    public OrderDetailViewDto setClient(String client) {
        this.client = client;
        return this;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public OrderDetailViewDto setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public LocalDateTime getDeliveredOn() {
        return deliveredOn;
    }

    public OrderDetailViewDto setDeliveredOn(LocalDateTime deliveredOn) {
        this.deliveredOn = deliveredOn;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public OrderDetailViewDto setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public OrderDetailViewDto setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public CartEntity getCart() {
        return cart;
    }

    public OrderDetailViewDto setCart(CartEntity cart) {
        this.cart = cart;
        return this;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public OrderDetailViewDto setDelivered(Boolean delivered) {
        isDelivered = delivered;
        return this;
    }
}
