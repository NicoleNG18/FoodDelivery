package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser;

    private ProductEntity burgerOne, pizzaOne;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("user1@user.com", "user1");

        burgerOne = testDataUtils.createProductBurger("burgerOne");
        pizzaOne = testDataUtils.createProductPizza("pizzaOne");

        testDataUtils.addProduct(testUser, burgerOne);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }


    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void testGetCart_ShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cart"))
                .andExpect(view().name("order-cart"))
                .andExpect(model().attributeExists("cartProducts", "productsPrice"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void testAddingProductToCart_WorksCorrectly() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/cart/add/{id}", pizzaOne.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/pizza"));

    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void testRemoveFromCart_WorksCorrectly() throws Exception {

        mockMvc.perform(patch("/cart/remove/{id}", burgerOne.getId())
                        .param("id", burgerOne.getId().toString()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart"));

    }

}