package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.enums.FoodCategoryEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String getMenu() {
        return "menu-categories";
    }

    @GetMapping("/menu/{category}")
    public String getCategoryPage(@PathVariable
                                  FoodCategoryEnum category,
                                  Model model) {

        model.addAttribute("category",category);

        return "category-page";
    }
}
