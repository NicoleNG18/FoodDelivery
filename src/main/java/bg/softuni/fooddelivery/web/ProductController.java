package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
                                  Model model)
                                  /*@PageableDefault(
                                          page = 0,
                                          size = 5)
                                      Pageable pageable)*/ {

        model.addAttribute("category",category);
        model.addAttribute("products",this.productService.allProducts(category));
//        model.addAttribute("countPr",this.cartRepository.count());

        return "categories-page";
    }



}
