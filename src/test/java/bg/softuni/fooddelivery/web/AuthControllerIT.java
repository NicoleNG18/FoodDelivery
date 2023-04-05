package bg.softuni.fooddelivery.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;

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
}
