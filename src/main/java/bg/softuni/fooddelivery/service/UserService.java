package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.model.UserModelDto;
import bg.softuni.fooddelivery.domain.dto.binding.UserRegistrationDTO;
import bg.softuni.fooddelivery.domain.entities.ShoppingCartEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    private final CartService cartService;

    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserRoleService userRoleService,
                       CartService cartService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.cartService = cartService;
    }

    public void registerUser(UserModelDto userToRegister) {

        UserEntity userToSave = this.mapToUser(userToRegister);
        UserRoleEntity userRole = this.userRoleService.getRoleByType(UserRoleEnum.USER);
        ShoppingCartEntity shoppingCart = this.cartService.getNewCart();

        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()))
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)))
                .setShoppingCart(shoppingCart);

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findUserEntityByUsername(username).orElse(null);
    }

    public UserModelDto mapToModel(UserRegistrationDTO user) {
        return this.modelMapper.map(user, UserModelDto.class);
    }

    public UserEntity mapToUser(UserModelDto modelDto) {
        return this.modelMapper.map(modelDto, UserEntity.class);
    }
}
