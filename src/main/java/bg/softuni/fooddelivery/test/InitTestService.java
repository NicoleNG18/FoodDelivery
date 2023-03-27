package bg.softuni.fooddelivery.test;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import bg.softuni.fooddelivery.service.CartService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitTestService {

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    private final CartService cartService;
//    private final String defaultPass;

    private final UserRepository userRepository;

    public InitTestService(UserRoleRepository userRoleRepository,
                           PasswordEncoder passwordEncoder,
                           CartService cartService,
                           UserRepository userRepository)
                          /*@Value("${fooddelivery.admin.defaultpass}") String defaultPass)*/ {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.userRepository = userRepository;
//        this.defaultPass=defaultPass;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initAdmin();
    }

    public void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            this.userRoleRepository.saveAllAndFlush(getUserRoles());
        }
    }

    private static List<UserRoleEntity> getUserRoles() {
        List<UserRoleEntity> roles = new ArrayList<>();
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.ADMIN));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.USER));
        roles.add(new UserRoleEntity().setRole(UserRoleEnum.WORKER));
        return roles;
    }

    public void initAdmin() {
        if (this.userRepository.count() == 0) {
            UserEntity user = new UserEntity()
                    .setFirstName("user")
                    .setLastName("userov")
                    .setAge(25)
                    .setEmail("user@user.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("user")
                    .setPhoneNumber("0515051501")
                    .setRoles(userRoleRepository.findAll())
                    .setCart(this.cartService.getNewCart());

            this.userRepository.saveAndFlush(user);
        }
    }

}
