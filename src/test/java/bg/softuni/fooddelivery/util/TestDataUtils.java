package bg.softuni.fooddelivery.util;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;

@Component
public class TestDataUtils {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            UserRoleEntity workerRole = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
            userRoleRepository.save(workerRole);
        }
    }

    public UserEntity createTestAdmin(String email) {

        initRoles();

        UserEntity admin = new UserEntity()
                .setEmail(email)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }


    public UserEntity createTestUser(String email) {

        initRoles();

        UserEntity user = new UserEntity().
                setEmail(email).
                setFirstName("User").
                setLastName("Userov").
                setRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() == UserRoleEnum.USER).
                        toList());

        return userRepository.save(user);
    }



    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
    }

}
