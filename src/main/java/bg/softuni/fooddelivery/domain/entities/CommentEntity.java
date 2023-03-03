package bg.softuni.fooddelivery.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private OrderEntity order;

    public CommentEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public CommentEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CommentEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public CommentEntity setOrder(OrderEntity order) {
        this.order = order;
        return this;
    }
}
