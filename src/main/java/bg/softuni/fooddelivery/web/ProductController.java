package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.EditProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.binding.AddProductBindingDto;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService menuService) {
        this.productService = menuService;
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable("category")
                                  ProductCategoryEnum category,
                                  Model model) {

        model.addAttribute("category", category);
        model.addAttribute("products", this.productService.allProducts(category));

        return "categories-page";
    }

    @GetMapping("/products/add")
    public String addProduct() {
        return "add-product";
    }

    @ModelAttribute("productDto")
    public AddProductBindingDto initBindingDto() {
        return new AddProductBindingDto();
    }

    @ModelAttribute("editedProductDto")
    public EditProductBindingDto initEditProductBindingDto() {
        return new EditProductBindingDto();
    }

    @PostMapping("/products/add")
    public String postAddProduct(@Valid AddProductBindingDto productDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("productDto", productDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDto"
                    , bindingResult);

            return "redirect:/products/add";

        }

        this.productService.addProduct(productDto);

        return "redirect:/";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long productId,
                              Model model) {

        model.addAttribute("product", this.productService.getProductById(productId));

        return "edit-product";
    }


    @PatchMapping("/products/edited/{id}")
    public String editedProduct(@PathVariable("id") Long productId,
                                @Valid EditProductBindingDto editedProductDto,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
//TODO : errors?
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("editedProductDto", editedProductDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedProductDto"
                    , bindingResult);

            return "redirect:/products/edit/{id}";

        }

        this.productService.editProduct(productId, editedProductDto);

        final String category = this.productService.getCategoryName(productId);

        return "redirect:/menu/" + category;
    }

    @DeleteMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {

        final String category = this.productService.getCategoryName(productId);

        this.productService.deleteProduct(productId);

        return "redirect:/menu/" + category;
    }

}
