package bg.softuni.fooddelivery.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRegisterControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRegisterShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")
                .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    void testRegistrationWorksCorrectly() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("firstName", "Petur")
                .param("lastName", "Petrov")
                .param("username", "peturr")
                .param("email", "pesho@example")
                .param("password", "topsecret")
                .param("confirmPassword", "topsecret")
                .param("age", "25")
                .param("phoneNumber", "07894563210")
                .param("gender", "FEMALE")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/login"));

    }

    @Test
    void testRegistrationWithInvalidData() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("firstName", "")
                        .param("lastName", "Petrov")
                        .param("username", "peturr")
                        .param("email", "pesho@example")
                        .param("password", "topsecret")
                        .param("confirmPassword", "topsecret")
                        .param("age", "25")
                        .param("phoneNumber", "07894563210")
                        .param("gender", "FEMALE")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

    }


}
