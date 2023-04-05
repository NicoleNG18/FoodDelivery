package bg.softuni.fooddelivery.web;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;


    @BeforeEach
    void setUp() {
        UserEntity testAdmin = testDataUtils.createTestAdmin("adminMenu@admin.com", "adminMenu");
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }


    @Test
    void testGetMenuShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-categories"));
    }

    @Test
    @WithMockUser(username = "adminMenu", roles = {"USER", "ADMIN", "WORKER"})
    void testGetMenuShowsUp_WithLoggedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-categories"))
                .andExpect(model().attributeExists("countProducts"));
    }

    @Test
    void testGetMenuCategoriesShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/pizza"))
                .andExpect(view().name("categories-page"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category", "products"));
    }

    @Test
    @WithMockUser(username = "adminMenu", roles = {"USER", "ADMIN", "WORKER"})
    void testGetMenuCategoriesShowsUp_WithLoggedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/pizza"))
                .andExpect(view().name("categories-page"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("category", "products"));
    }

    @Test
    void testGetMenuCategoriesThrowsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu/non-existent"))
                .andExpect(view().name("category-does-not-exist"))
                .andExpect(status().is4xxClientError());
    }

}
