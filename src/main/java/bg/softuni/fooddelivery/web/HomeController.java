package bg.softuni.fooddelivery.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome(Principal principal,
                          Model model) {
        if (principal != null) {
            model.addAttribute("name", principal.getName());
        }

        return "index";
    }
}