package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal){


        model.addAttribute("user",this.userService.getUserByUsername(principal.getName()));


        return "user-profile";
    }
}
