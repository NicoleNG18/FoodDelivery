package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opentest4j.AssertionFailedError;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FoodDeliveryUserDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    private FoodDeliveryUserDetailsService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new FoodDeliveryUserDetailsService(mockUserRepository);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        //arrange
        UserEntity testUser = new UserEntity()
                .setUsername("admin")
                .setPassword("topsecret")
                .setEmail("admin@example.com")
                .setAge(25)
                .setPhoneNumber("07894561230")
                .setFirstName("Tester")
                .setLastName("Testov")
                .setGender(GenderEnum.MALE)
                .setRoles(List.of(
                        new UserRoleEntity().setRole(UserRoleEnum.ADMIN),
                        new UserRoleEntity().setRole(UserRoleEnum.WORKER),
                        new UserRoleEntity().setRole(UserRoleEnum.USER)
                ));

        when(mockUserRepository.findUserEntityByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        //act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        //assert
        Assertions.assertNotNull(userDetails);

        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());

        Assertions.assertEquals(3,userDetails.getAuthorities().size());
        assertRole(userDetails.getAuthorities(), "ROLE_ADMIN");
        assertRole(userDetails.getAuthorities(), "ROLE_WORKER");
        assertRole(userDetails.getAuthorities(), "ROLE_USER");
    }

    private void assertRole(Collection<? extends GrantedAuthority> authorities,
                            String role) {
        authorities.
                stream().
                filter(a -> role.equals(a.getAuthority())).
                findAny().
                orElseThrow(() -> new AssertionFailedError("Role " + role + " not found!"));
    }

    @Test
    void testLoadUserByUsername_UserDoesNotExist() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("non-existant@example.com")
        );
    }
}
