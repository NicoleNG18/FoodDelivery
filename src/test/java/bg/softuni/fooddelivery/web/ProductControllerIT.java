package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository mockProductRepository;

    public ProductControllerIT() {
    }

    @BeforeEach
    void setUp() {
        ProductEntity testProduct1 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(11.00))
                .setCategory(ProductCategoryEnum.pizza)
                .setDescription("descriptionnnnnn1")
                .setName("pizza test1");

        ProductEntity testProduct2 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(12.00))
                .setCategory(ProductCategoryEnum.pizza)
                .setDescription("descriptionnnnnn2")
                .setName("pizza test2");

        ProductEntity testProduct3 = new ProductEntity()
                .setPrice(BigDecimal.valueOf(13.00))
                .setCategory(ProductCategoryEnum.pizza)
                .setDescription("descriptionnnnnn3")
                .setName("pizza test3");

        mockProductRepository.saveAndFlush(testProduct1);
        mockProductRepository.saveAndFlush(testProduct2);
        mockProductRepository.saveAndFlush(testProduct3);
    }


    @Test
    void testGetMenuShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-categories"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testGetAddProductWorksCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"));
    }

    @Test
    void testGetMenuCategoriesShowsUp() throws Exception {
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

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testAddProductCorrectly() throws Exception {

        mockMvc.perform(post("/products/add")
                        .param("name", "Test pizza")
                        .param("description", "Test pizza description")
                        .param("category", "pizza")
                        .param("price", "10.00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testAddProductWithInvalidData() throws Exception {

        mockMvc.perform(post("/products/add")
                        .param("name", "")
                        .param("description", "Test pizza description")
                        .param("category", "pizza")
                        .param("price", "10.00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/add"));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testGetEditProductShowsUp() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/products/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-product"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testGetEditProductThrowsException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/products/edit/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN", "WORKER", "USER"})
    void testDeleteProductWorksCorrectly() throws Exception {

        String deletedMonitorItemID = String.valueOf(3);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/products/delete/{id}", deletedMonitorItemID)
                        .param("id", deletedMonitorItemID)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/pizza"));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "WORKER", "ADMIN"})
    void testUpdateProductWorksCorrectly() throws Exception {
        String deletedMonitorItemID = String.valueOf(1);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/edited/{id}", deletedMonitorItemID)
                        .with(csrf())
                        .param("description", "new descriptionnn")
                        .param("price", "600"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/menu/pizza"));

    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "WORKER", "ADMIN"})
    void testUpdateProductWithInvalidData() throws Exception {
        String deletedMonitorItemID = String.valueOf(1);

        mockMvc.perform(MockMvcRequestBuilders.patch("/products/edited/{id}", deletedMonitorItemID)
                        .with(csrf())
                        .param("description", "dddddd")
                        .param("price", "-600"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/edit/" + deletedMonitorItemID));

    }

}
