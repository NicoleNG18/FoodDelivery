package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.service.OrderService;
import bg.softuni.fooddelivery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.*;

@Controller
public class HomeController {
    private final UserService userService;
    private final OrderService orderService;

    public HomeController(UserService userService,
                          OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/")
    public String getHome(Principal principal,
                          Model model) {

        if (principal != null) {

            final UserEntity loggedUser = this.userService.getUserByUsername(principal.getName());

            model.addAttribute(NAME, loggedUser.getUsername());
            model.addAttribute(ORDERS, orderService.getInProgressOrdersByUser(loggedUser));
            model.addAttribute(COUNT_PRODUCTS, loggedUser.getCart().getCountProducts());

        }

        return "index";
    }

}