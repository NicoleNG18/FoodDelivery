package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService menuService;

    public ProductController(ProductService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable("category")
                                      ProductCategoryEnum category,
                                  Model model)
                                  /*@PageableDefault(
                                          page = 0,
                                          size = 5)
                                      Pageable pageable)*/ {

        model.addAttribute("category",category);
        model.addAttribute("products",this.menuService.allProducts(category));

        return "categories-page";
    }



}
