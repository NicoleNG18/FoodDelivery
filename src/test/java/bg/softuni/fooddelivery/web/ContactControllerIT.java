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
public class ContactControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetContactFormShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("contact-us"));
    }

    @Test
    void testContactFormWorksCorrectly() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Nikol")
                        .param("email", "email@example.com")
                        .param("subject", "Test subject")
                        .param("description", "Test description")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

    }

    @Test
    void testContactWithInvalidData() throws Exception {
        mockMvc.perform(post("/contact")
                        .param("name", "Nikol")
                        .param("email", "email@example.com")
                        .param("subject", "Test subject")
                        .param("description", "T")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/contact"));

    }

}

