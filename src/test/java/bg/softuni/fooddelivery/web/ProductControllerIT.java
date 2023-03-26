package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.AddProductBindingDto;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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


    @Test
    void testGetMenuShowsUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/menu"))
                .andExpect(status().isOk())
                .andExpect(view().name("menu-categories"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"USER", "ADMIN", "WORKER"})
    void testAddProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-product"));
    }

    @Test
    void testGetMenuCategoriesWorks() throws Exception {
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
    void testAddProductNotCorrectly() throws Exception {

        mockMvc.perform(post("/products/add")
                        .param("name", "")
                        .param("description", "Test pizza description")
                        .param("category", "pizza")
                        .param("price", "10.00")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/products/add"));

    }

//
//    @GetMapping("/products/edit/{id}")
//    public String editProduct(@PathVariable("id") Long productId,
//                              Model model) {
//
//        model.addAttribute("product", this.productService.getProductById(productId));
//
//        return "edit-product";
//    }
//
//
//    @PatchMapping("/products/edited/{id}")
//    public String editedProduct(@PathVariable("id") Long productId,
//                                @Valid EditProductBindingDto editedProductDto,
//                                BindingResult bindingResult,
//                                RedirectAttributes redirectAttributes) {
//
//        if (bindingResult.hasErrors()) {
//
//            redirectAttributes.addFlashAttribute("editedProductDto", editedProductDto);
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto"
//                    , bindingResult);
//
//            return "redirect:/products/edit/{id}";
//
//        }
//
//        this.productService.editProduct(productId, editedProductDto);
//
//        final String category = this.productService.getCategoryName(productId);
//
//        return "redirect:/menu/" + category;
//    }
//
//    @DeleteMapping("/products/delete/{id}")
//    public String deleteProduct(@PathVariable("id") Long productId) {
//
//        final String category = this.productService.getCategoryName(productId);
//
//        this.productService.deleteProduct(productId);
//
//        return "redirect:/menu/" + category;
//    }


}
