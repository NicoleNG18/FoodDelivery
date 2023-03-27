package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    UserEntity userEntity;

    @BeforeEach
    void setUp() {

        CartEntity cart = new CartEntity();
        shoppingCartRepository.saveAndFlush(cart);

        UserRoleEntity roleAdmin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity roleUser = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity roleWorker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

        userRoleRepository.saveAndFlush(roleAdmin);
        userRoleRepository.saveAndFlush(roleUser);
        userRoleRepository.saveAndFlush(roleWorker);

        userEntity = new UserEntity().setPassword("topsecret")
                .setGender(GenderEnum.FEMALE)
                .setFirstName("tester")
                .setLastName("testerov")
                .setEmail("test@mail.com")
                .setPhoneNumber("phoneeeeeee")
                .setAge(25)
                .setUsername("testUser")
                .setOrders(new ArrayList<>())
                .setRoles(List.of(roleAdmin, roleUser, roleWorker))
                .setCart(cart);

        userRepository.save(userEntity);


    }

    @Test
    void testGetLoginShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }


    @Test
    @WithAnonymousUser
    void testLoginWithWrongInput_ShouldRedirectBackToLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("username", "Pato")
                        .param("password", "123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    void testGetUserProfileShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void testUserGetProfileWithIdShowsUp() throws Exception {
        String id = "1";
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("user"));
    }
//exc
    @Test
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void testUserGetProfileWithIdThrowsException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void testGetAllUsersPageShowsUp() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser
    void testGetChangeRolesWorksCorrectly() throws Exception {

        UserRoleEntity roleAdmin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity roleUser = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity roleWorker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

        userRoleRepository.saveAndFlush(roleAdmin);
        userRoleRepository.saveAndFlush(roleUser);
        userRoleRepository.saveAndFlush(roleWorker);

        CartEntity cart = new CartEntity();
        shoppingCartRepository.saveAndFlush(cart);

        UserEntity userToTest = new UserEntity().setPassword("topsecret2")
                .setGender(GenderEnum.FEMALE)
                .setFirstName("tester2")
                .setLastName("testerov2")
                .setEmail("test2@mail.com")
                .setPhoneNumber("phoneeeeeee2")
                .setAge(22)
                .setUsername("testUser2")
                .setOrders(new ArrayList<>())
                .setRoles(List.of(roleUser))
                .setCart(cart);

        userRepository.save(userToTest);

        mockMvc.perform(get("/users/change/2"))
                .andExpect(view().name("roles-change"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));


    }
//exc
    @Test
    @WithMockUser
    void testGetChangeRolesThrowsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/change/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }


    @Test
    @WithMockUser
    void testGetEditUserWorksCorrectly() throws Exception {

        UserRoleEntity roleAdmin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity roleUser = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity roleWorker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

        userRoleRepository.saveAndFlush(roleAdmin);
        userRoleRepository.saveAndFlush(roleUser);
        userRoleRepository.saveAndFlush(roleWorker);

        CartEntity cart = new CartEntity();
        shoppingCartRepository.saveAndFlush(cart);

        UserEntity userToTest = new UserEntity().setPassword("topsecret2")
                .setGender(GenderEnum.FEMALE)
                .setFirstName("tester2")
                .setLastName("testerov2")
                .setEmail("test2@mail.com")
                .setPhoneNumber("phoneeeeeee2")
                .setAge(22)
                .setUsername("testUser2")
                .setOrders(new ArrayList<>())
                .setRoles(List.of(roleUser))
                .setCart(cart);

        userRepository.save(userToTest);

        mockMvc.perform(get("/users/edit/2"))
                .andExpect(view().name("edit-user"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));


    }
//exc
    @Test
    @WithMockUser
    void testGetEditUserThrowsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }


    @Test
    @WithMockUser
    void testRemovingRoleWorksCorrectly() throws Exception {
//
        UserRoleEntity roleAdmin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity roleUser = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity roleWorker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

        userRoleRepository.saveAndFlush(roleAdmin);
        userRoleRepository.saveAndFlush(roleUser);
        userRoleRepository.saveAndFlush(roleWorker);

//        UserRoleEntity roleUser = userRoleRepository.findByRole(UserRoleEnum.USER);
//        UserRoleEntity roleAdmin = userRoleRepository.findByRole(UserRoleEnum.ADMIN);
//        UserRoleEntity roleWorker = userRoleRepository.findByRole(UserRoleEnum.WORKER);

        CartEntity cart = new CartEntity();
        shoppingCartRepository.saveAndFlush(cart);

        UserEntity userToTest = new UserEntity().setPassword("topsecret2")
                .setGender(GenderEnum.FEMALE)
                .setFirstName("tester2")
                .setLastName("testerov2")
                .setEmail("test2@mail.com")
                .setPhoneNumber("phoneeeeeee2")
                .setAge(22)
                .setUsername("testUser2")
                .setOrders(new ArrayList<>())
                .setRoles(List.of(roleUser, roleAdmin, roleWorker))
                .setCart(cart);

        userRepository.save(userToTest);

        mockMvc.perform(patch("/users/roles/remove/2").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/change/2"));

    }

//    @Test
//    @WithMockUser
//    void testAddingRoleWorksCorrectly() throws Exception {
//
//        UserRoleEntity admin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
//        UserRoleEntity user = new UserRoleEntity().setRole(UserRoleEnum.USER);
//        UserRoleEntity worker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);
//
//        userRoleRepository.saveAndFlush(admin);
//        userRoleRepository.saveAndFlush(user);
//        userRoleRepository.saveAndFlush(user);
//
//
//        UserRoleEntity roleAdmin=userRoleRepository.findByRole(UserRoleEnum.ADMIN);
//        UserRoleEntity roleUser=userRoleRepository.findByRole(UserRoleEnum.USER);
//        UserRoleEntity roleWorker=userRoleRepository.findByRole(UserRoleEnum.WORKER);
//
//
//        CartEntity cart = new CartEntity();
//        shoppingCartRepository.saveAndFlush(cart);
//
//        UserEntity userToTest = new UserEntity().setPassword("topsecret3")
//                .setGender(GenderEnum.FEMALE)
//                .setFirstName("tester3")
//                .setLastName("testerov3")
//                .setEmail("test3@mail.com")
//                .setPhoneNumber("phoneeeeeee3")
//                .setAge(23)
//                .setUsername("testUser3")
//                .setOrders(new ArrayList<>())
//                .setRoles(List.of(roleUser,roleAdmin))
//                .setCart(cart);
//
//        userRepository.save(userToTest);
//
//        mockMvc.perform(patch("/users/roles/add/2").with(csrf()).param("id","2"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/users/change/2"));
//
//    }


    @Test
    @WithMockUser
    void testEditedUserWorksCorrectly() throws Exception {
        String editedUserId = String.valueOf(1);


        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", editedUserId)
                        .with(csrf())
                        .param("username", "newUsername"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile/1"));
    }

    @Test
    @WithMockUser
    void testEditedUserWithInvalidData() throws Exception {
        String editedUserId = String.valueOf(1);


        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", editedUserId)
                        .with(csrf())
                        .param("username", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/edit/1"));
    }

    //exc
    @Test
    @WithMockUser
    void testEditedUserWithSameUsername() throws Exception {
        String editedUserId = String.valueOf(1);

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", editedUserId)
                        .with(csrf())
                        .param("username", "testUser"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/edit/1"));
    }


}
