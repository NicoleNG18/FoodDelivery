package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOwner_Id(Long id);

    List<OrderEntity> findAllByStatusAndOwner_Id(OrderStatusEnum orderStatusEnum, Long id);
}
