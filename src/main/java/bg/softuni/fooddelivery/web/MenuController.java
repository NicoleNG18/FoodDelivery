package bg.softuni.fooddelivery.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/menu")
    public String getMenu(){
        return "menu-categories";
    }
}
