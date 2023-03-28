package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.util.TestDataUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    UserEntity testUser, testAdmin, testWorker;

    @Autowired
    private TestDataUtils testDataUtils;

    @BeforeEach
    void setUp() {
        testUser = testDataUtils.createTestUser("userCtr@user.com", "testUserCtr");
        testAdmin = testDataUtils.createTestAdmin("adminUserCtr@admin.com", "adminUserCtrl");
        testWorker = testDataUtils.createTestWorker("workerUserCtr@worker.com", "workerUserCtrl");
    }
    @AfterEach
    void tearDown() {
        testDataUtils.cleanUpDatabase();
    }

    @Test
    @WithAnonymousUser
    void testGetLoginShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    @WithAnonymousUser
    void testLoginWithWrongInput_ShouldRedirectBackToLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/users/login-error")
                        .param("username", "userTest")
                        .param("password", "123")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "testUserCtr", roles = "USER")
    void testGetUserProfileShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testUserGetProfileWithIdShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/{id}", testUser.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = "ADMIN")
    void testUserGetProfileWithIdThrowsException() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testGetAllUsersPageShowsUp() throws Exception {
        mockMvc.perform(get("/users/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-users"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testGetChangeRolesWorksCorrectly() throws Exception {
        mockMvc.perform(get("/users/change/{id}", testUser.getId()))
                .andExpect(view().name("roles-change"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser
    void testGetChangeRolesThrowsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/change/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testGetEditUserWorksCorrectly() throws Exception {
        mockMvc.perform(get("/users/edit/{id}", testUser.getId()))
                .andExpect(view().name("edit-user"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"));
    }

    @Test
    @WithMockUser
    void testGetEditUserThrowsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/edit/28"))
                .andExpect(status().is4xxClientError())
                .andExpect(view().name("object-not-found"))
                .andExpect(model().attributeExists("objectId", "objectType"));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testRemovingRoleWorksCorrectly() throws Exception {
        mockMvc.perform(patch("/users/roles/remove/{id}", testWorker.getId()).with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/change/" + testWorker.getId().toString()));

    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testAddingRoleWorksCorrectly() throws Exception {
        mockMvc.perform(patch("/users/roles/add/{id}", testUser.getId()
                ).with(csrf()).param("id", testUser.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/change/" + testUser.getId().toString()));

    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testEditedUserWorksCorrectly() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", testUser.getId())
                        .with(csrf())
                        .param("username", "newUsername"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/profile/" + testUser.getId().toString()));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testEditedUserWithInvalidData() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", testUser.getId())
                        .with(csrf())
                        .param("username", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/edit/" + testUser.getId()));
    }

    @Test
    @WithMockUser(username = "adminUserCtrl", roles = "ADMIN")
    void testEditedUserWithSameUsername() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.patch("/users/edited/{id}", testUser.getId())
                        .with(csrf())
                        .param("username", "testUserCtr"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/edit/" + testUser.getId()));
    }

}
