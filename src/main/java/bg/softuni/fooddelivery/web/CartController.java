package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.CartService;
import bg.softuni.fooddelivery.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService,
                          ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long id,
                            Principal principal,
                            Model model) {

        String category = this.productService.getCategory(id);

        long count = this.cartService.addToCart(id, principal);

        return "redirect:/menu/" + category;
    }
}
