package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.OrderRepository;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void init() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext).apply(springSecurity())
                .build();
    }

    public UserEntity setUpEntity() {

        CartEntity cart = new CartEntity();
        shoppingCartRepository.saveAndFlush(cart);

        UserRoleEntity roleAdmin = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity roleUser = new UserRoleEntity().setRole(UserRoleEnum.USER);
        UserRoleEntity roleWorker = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

        userRoleRepository.saveAndFlush(roleAdmin);
        userRoleRepository.saveAndFlush(roleUser);
        userRoleRepository.saveAndFlush(roleWorker);

        UserEntity user = new UserEntity().setPassword("topsecret")
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

        userRepository.save(user);

        return user;
    }

//    @Test
////    @WithMockUser(username = "admin", password = "topsecret", authorities = {"USER", "WORKER", "ADMIN"})
//    void testGetOrderDetailsShowsUp() throws Exception {
//
//        OrderEntity order = new OrderEntity()
//                .setComment("commment")
//                .setStatus(OrderStatusEnum.IN_PROGRESS)
//                .setAddress("addresss")
//                .setCreatedOn(LocalDateTime.now())
//                .setContactNumber("numebrrrrr")
//                .setDeliveredOn(LocalDateTime.now())
//                .setPrice(BigDecimal.TEN);
//        orderRepository.saveAndFlush(order);
//
//        String id = String.valueOf(1);
//        mockMvc.perform(MockMvcRequestBuilders.get("/orders/details/{id}", id)
//                        .param("id", id).with(user(setUpEntity().getUsername())))
//                .andExpect(status().isOk())
//                .andExpect(view().name("order-details-api"))
//                .andExpect(model().attributeExists("idAtr", "order"));
//    }


}
