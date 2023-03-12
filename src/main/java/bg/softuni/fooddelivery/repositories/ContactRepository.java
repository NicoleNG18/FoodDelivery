package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {
}
