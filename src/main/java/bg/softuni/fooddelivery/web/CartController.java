package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long id,
                                Principal principal){

        this.cartService.addToCart(id,principal);

        return "redirect:/menu";
    }
}
