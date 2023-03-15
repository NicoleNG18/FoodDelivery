package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ShoppingCartRepository cartRepository;

    public HomeController(ShoppingCartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/")
    public String getHome(Model model){

        model.addAttribute("countPr",this.cartRepository.count());
        return "index";
    }
}
