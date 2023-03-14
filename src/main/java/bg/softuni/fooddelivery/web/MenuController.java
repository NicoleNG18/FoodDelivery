package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable
                                      ProductCategoryEnum category,
                                  Model model
                                 /* Pageable pageable*/) {

        model.addAttribute("category",category);
        model.addAttribute("foods",this.menuService.allFoodsByCategory(category));
        model.addAttribute("drinks",this.menuService.allDrinks());

        return "categories-page";
    }


}
