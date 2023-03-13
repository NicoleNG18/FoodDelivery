package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleEntity getRoleByType(UserRoleEnum role){
        return this.userRoleRepository.findByRole(role);
    }
}
