package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.ProductBindingDto;
import bg.softuni.fooddelivery.domain.dto.binding.UserRegistrationDTO;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ShoppingCartRepository cartRepository;

    public ProductController(ProductService menuService,
                             ShoppingCartRepository cartRepository) {
        this.productService = menuService;
        this.cartRepository = cartRepository;
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
    public String addProduct(){
        return "add-product";
    }

    @ModelAttribute("productDto")
    public ProductBindingDto initBindingDto() {
        return new ProductBindingDto();
    }

    @PostMapping("/products/add")
    public String postAddProduct(@Valid ProductBindingDto productDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){

            redirectAttributes.addFlashAttribute("productDto",productDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productDto"
                    ,bindingResult);

            return "redirect:/products/add";

        }

        this.productService.addProduct(productDto);

        return "redirect:/";
    }


}
