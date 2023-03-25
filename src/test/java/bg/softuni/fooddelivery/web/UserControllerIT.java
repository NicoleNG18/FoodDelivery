package bg.softuni.fooddelivery.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetLoginShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")
                        .with(csrf())).andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

//    @Test
//    void testGetProfileShowsUp() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile"))
//    csrf
//                .andExpect(status().isOk())
//                .andExpect(view().name("user-profile"))
//                .andExpect(model().attributeExists("user"));
//    }

//    @Test
//    void testGetAllUsersShowsUp() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users/all")
//                        .with(csrf()))
//                .andExpect(status().isOk())
//                .andExpect(view().name("all-users"));
////                .andExpect(model().attributeExists("users"));
//    }
}
