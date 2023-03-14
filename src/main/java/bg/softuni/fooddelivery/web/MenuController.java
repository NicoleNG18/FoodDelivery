package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.service.MenuService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String getCategoryPage(@PathVariable("category")
                                      String category,
                                  Model model,
                                  @PageableDefault(
                                          page = 0,
                                          size = 5)
                                      Pageable pageable) {

        model.addAttribute("category",category);
//        model.addAttribute("foods",this.menuService.allFoodsByCategory(category,pageable));
        model.addAttribute("drinks",this.menuService.allDrinks(pageable));

        return "categories-page";
    }



}
