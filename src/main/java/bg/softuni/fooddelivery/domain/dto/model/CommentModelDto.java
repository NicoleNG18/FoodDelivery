package bg.softuni.fooddelivery.domain.dto.model;

import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;

public class CommentModelDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public CommentModelDto setId(Long id) {
        this.id = id;
        return this;
    }

    private String description;

    private UserModelDto user;

    private OrderModelDto order;

    public CommentModelDto() {
    }

    public String getDescription() {
        return description;
    }

    public CommentModelDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public UserModelDto getUser() {
        return user;
    }

    public CommentModelDto setUser(UserModelDto user) {
        this.user = user;
        return this;
    }

    public OrderModelDto getOrder() {
        return order;
    }

    public CommentModelDto setOrder(OrderModelDto order) {
        this.order = order;
        return this;
    }
}
