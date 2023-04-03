package bg.softuni.fooddelivery.domain.dto.view;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;

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

    private OrderStatusEnum status;

    private String client;

    private String discount;

    private String comment;


    public OrderDetailViewDto() {
    }

    public String getComment() {
        return comment;
    }

    public OrderDetailViewDto setComment(String comment) {
        this.comment = comment;
        return this;
    }


    public String getDiscount() {
        return discount;
    }

    public OrderDetailViewDto setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderDetailViewDto setId(Long id) {
        this.id = id;
        return this;
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

    public OrderDetailViewDto setStatus(OrderStatusEnum status) {
        this.status = status;
        return this;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }
}


