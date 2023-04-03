package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.service.ProductService;
import bg.softuni.fooddelivery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final ProductService productService;
    private final UserService userService;

    public MenuController(ProductService productService,
                          UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String getMenu(Principal principal,
                          Model model) {

        if (principal != null) {
            model.addAttribute(COUNT_PRODUCTS,
                    this.userService
                            .getUserByUsername(principal.getName())
                            .getCart().getCountProducts());
        }

        return "menu-categories";
    }

    @GetMapping("/{category}")
    public String getMenuCategoriesPage(@PathVariable("category")
                                  String category,
                                        Model model,
                                        Principal principal) {

        model.addAttribute(CATEGORY, this.productService.findCategory(category));
        model.addAttribute(PRODUCTS, this.productService.allProducts(ProductCategoryEnum.valueOf(category)));

        if (principal != null) {
            model.addAttribute(COUNT_PRODUCTS,
                    this.userService
                            .getUserByUsername(principal.getName())
                            .getCart().getCountProducts());
        }

        return "categories-page";
    }

}
