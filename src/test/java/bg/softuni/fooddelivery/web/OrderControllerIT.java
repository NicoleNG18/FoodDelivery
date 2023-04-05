package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.OrderEntity;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataUtils testDataUtils;

    private UserEntity testUser, testAdmin;

    private OrderEntity testOrder;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("userOrder@user.com", "userOrderCtrl");
        testAdmin = testDataUtils.createTestAdmin("adminOrder@admin.com", "adminOrder");
        testOrder = testDataUtils.createOrder(testUser);
    }

    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testGetFinalize_ShowsUp() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/orders/finalize"))
                .andExpect(status().isOk())
                .andExpect(view().name("finalize-order"))
                .andExpect(model().attributeExists("foodPrice", "countProducts"));

    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testFinalizeOrder_WorksCorrectly() throws Exception {
        mockMvc.perform(post("/orders/finalize")
                        .param("comment", "Test comment")
                        .param("address", "Test address1")
                        .param("discount", "PIZZA4")
                        .param("contactNumber", "0789654123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testFinalizeOrder_WithInvalidData() throws Exception {
        mockMvc.perform(post("/orders/finalize")
                        .param("comment", "Test comment")
                        .param("address", "Test address1")
                        .param("discount", "")
                        .param("contactNumber", "")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders/finalize"));
    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testGetOrdersHistory_ShowsUp() throws Exception {
        mockMvc.perform(get("/orders/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders-history-user"))
                .andExpect(model().attributeExists("orders"));
    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testGetOrderDetails_ShowsUp() throws Exception {
        mockMvc.perform(get("/orders/details/{id}", testOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("order-details-api"))
                .andExpect(model().attributeExists("order", "idAtr"));
    }

    @Test
    @WithMockUser(username = "userOrderCtrl", roles = "USER")
    void testGetOrderDetails_ThrowsException() throws Exception {
        mockMvc.perform(get("/orders/details/789"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "adminOrder", roles = {"USER", "ADMIN", "WORKER"})
    void testGetAllOrdersHistory_ShowsUp() throws Exception {
        mockMvc.perform(get("/orders/all/history"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders-history"))
                .andExpect(model().attributeExists("allOrders"));
    }

}
