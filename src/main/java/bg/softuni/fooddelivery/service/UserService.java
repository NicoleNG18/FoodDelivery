package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.model.UserModelDto;
import bg.softuni.fooddelivery.domain.dto.binding.UserRegistrationDTO;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    public UserService(ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserRoleService userRoleService) {
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public void registerUser(UserModelDto userToRegister) {

        UserEntity userToSave=this.mapToUser(userToRegister);
        UserRoleEntity userRole=this.userRoleService.getRoleByType(UserRoleEnum.USER);

        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()))
                .setRoles(new ArrayList<>(Collections.singletonList(userRole)));

        this.userRepository.saveAndFlush(userToSave);
    }

    public UserModelDto mapToModel(UserRegistrationDTO user) {
        return this.modelMapper.map(user, UserModelDto.class);
    }

    public UserEntity mapToUser(UserModelDto modelDto){
        return this.modelMapper.map(modelDto, UserEntity.class);
    }
}
