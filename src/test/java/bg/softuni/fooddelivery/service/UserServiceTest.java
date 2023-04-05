package bg.softuni.fooddelivery.service;


import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService serviceToTest;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserRoleService mockUserRoleService;
    @Mock
    private UserRoleRepository mockUserRoleRepository;
    @Mock
    private CartService cartService;


    @BeforeEach
    void setUp() {
        serviceToTest = new
                UserService(mockModelMapper, mockPasswordEncoder, mockUserRepository, mockUserRoleService, mockUserRoleRepository, cartService);
    }

    @Test
    void getUserByIdException() {
        Assertions.assertThrows(NotFoundObjectException.class,
                () -> this.serviceToTest.getUserById(-1L));
    }


}
