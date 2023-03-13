package bg.softuni.fooddelivery.repositories;

import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {


    UserRoleEntity findByRole(UserRoleEnum role);
}
