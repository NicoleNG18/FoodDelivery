package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}

