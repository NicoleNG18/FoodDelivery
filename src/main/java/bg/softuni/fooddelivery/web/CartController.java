package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.CartService;
import bg.softuni.fooddelivery.service.OrderService;
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
    private final OrderService orderService;

    public CartController(CartService cartService,
                          ProductService productService,
                          OrderService orderService) {
        this.cartService = cartService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/cart")
    public String getCart(Model model, Principal principal){


        model.addAttribute("cartProducts",this.orderService.getProducts(principal));
        model.addAttribute("productsPrice",this.orderService.getProductsPrice(principal));

        return "order-cart";
    }

    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable("id") Long id,
                            Principal principal) {

        final String category = this.productService.getCategoryName(id);

        this.cartService.addToCart(id, principal);

        return "redirect:/menu/" + category;
    }

    @GetMapping("/cart/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id,
                                 Principal principal){

        this.cartService.removeFromCart(id,principal);

        return "redirect:/cart";
    }
}
