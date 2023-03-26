package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.entities.UserRoleEntity;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import bg.softuni.fooddelivery.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

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
    @WithMockUser
    void testGetCartShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(view().name("order-cart"))
                .andExpect(model().attributeExists("cartProducts", "productsPrice"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void testRemoveFromCartWorksCorrectly() throws Exception {

        String deletedProductId = String.valueOf(1);

        ProductEntity productTest1 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(11))
                .setCategory(ProductCategoryEnum.burger)
                .setName("BurgerTest1")
                .setDescription("BurgerDescription1");

        productRepository.saveAndFlush(productTest1);


        CartEntity cart = new CartEntity().setProducts(List.of(productTest1));

        shoppingCartRepository.saveAndFlush(cart);

        mockMvc.perform(patch("/cart/remove/{id}", deletedProductId)
                        .param("id", deletedProductId).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));


    }
//
    @Test
    @WithMockUser
    void testAddingProductToCartWorksCorrectly() throws Exception {

        String category = "burger";
        String addedProductId = String.valueOf(2);

        ProductEntity productTest1 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(11))
                .setCategory(ProductCategoryEnum.burger)
                .setName("BurgerTest1")
                .setDescription("BurgerDescription1");

        productRepository.saveAndFlush(productTest1);

        CartEntity cart = new CartEntity().setProducts(List.of(productTest1));

        shoppingCartRepository.saveAndFlush(cart);


        ProductEntity productTest2 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(12))
                .setCategory(ProductCategoryEnum.burger)
                .setName("BurgerTest2")
                .setDescription("BurgerDescription2");

        productRepository.saveAndFlush(productTest2);

        mockMvc.perform(MockMvcRequestBuilders.patch("/cart/add/{id}", addedProductId).with(csrf())
                        .param("id", addedProductId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/" + category));


    }

}
