package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByOwner_Id(Long id);
}
