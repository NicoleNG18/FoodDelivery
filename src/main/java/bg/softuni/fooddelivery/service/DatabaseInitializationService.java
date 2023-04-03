package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatabaseInitializationService {

    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final UserRepository userRepository;

    public DatabaseInitializationService(UserRoleRepository userRoleRepository,
                                         PasswordEncoder passwordEncoder,
                                         CartService cartService,
                                         UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initAdmin();
        initWorkers();
        initUsers();
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
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setAge(25)
                    .setEmail("admin@admin.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("admin")
                    .setPhoneNumber("0515051501")
                    .setRoles(userRoleRepository.findAll())
                    .setCart(this.cartService.getNewCart());

            this.userRepository.saveAndFlush(user);
        }
    }

    public void initWorkers() {
        if (this.userRepository.count() == 1) {

            UserEntity workerOne = new UserEntity()
                    .setFirstName("Worker")
                    .setLastName("One")
                    .setAge(25)
                    .setEmail("worker@one.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("workerOne")
                    .setPhoneNumber("0515051502")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() != UserRoleEnum.ADMIN)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            UserEntity workerTwo = new UserEntity()
                    .setFirstName("Worker")
                    .setLastName("Two")
                    .setAge(25)
                    .setEmail("worker@two.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.FEMALE)
                    .setUsername("workerTwo")
                    .setPhoneNumber("0515051503")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() != UserRoleEnum.ADMIN)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            this.userRepository.saveAndFlush(workerOne);
            this.userRepository.saveAndFlush(workerTwo);
        }

    }

    private void initUsers() {
        if (this.userRepository.count() == 3) {

            UserEntity userOne = new UserEntity()
                    .setFirstName("Nikol")
                    .setLastName("Georgieva")
                    .setAge(25)
                    .setEmail("nikol@user.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("nikol")
                    .setPhoneNumber("0515051504")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() == UserRoleEnum.USER)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            UserEntity userTwo = new UserEntity()
                    .setFirstName("Ivan")
                    .setLastName("Ivanov")
                    .setAge(25)
                    .setEmail("ivan@user.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("ivan")
                    .setPhoneNumber("0515051504")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() == UserRoleEnum.USER)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            UserEntity testUser = new UserEntity()
                    .setFirstName("Testerche")
                    .setLastName("Testerov")
                    .setAge(25)
                    .setEmail("testAppUser@user.com")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setGender(GenderEnum.MALE)
                    .setUsername("testAppUser")
                    .setPhoneNumber("0515051504")
                    .setRoles(userRoleRepository.findAll()
                            .stream()
                            .filter(r -> r.getRole() == UserRoleEnum.USER)
                            .collect(Collectors.toList()))
                    .setCart(this.cartService.getNewCart());

            this.userRepository.saveAndFlush(userOne);
            this.userRepository.saveAndFlush(userTwo);
            this.userRepository.saveAndFlush(testUser);
        }
    }

}
