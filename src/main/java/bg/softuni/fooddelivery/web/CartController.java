package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.CartService;
import bg.softuni.fooddelivery.service.OrderService;
import bg.softuni.fooddelivery.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.*;

@Controller
@RequestMapping("/cart")
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

    @GetMapping
    public String getCart(Model model,
                          Principal principal) {

        model.addAttribute(CART_PRODUCTS, this.orderService.getProducts(principal));
        model.addAttribute(PRODUCTS_PRICE, this.orderService.getProductsPrice(principal));
        model.addAttribute(COUNT_PRODUCTS, this.orderService.getProducts(principal).size());

        return "order-cart";
    }

    @PatchMapping("/add/{id}")
    public String addProductToTheCart(@PathVariable("id") Long id,
                                      Principal principal) {

        this.cartService.addToCart(id, principal);

        return "redirect:/menu/" + this.productService.getCategoryName(id);
    }

    @PatchMapping("/remove/{id}")
    public String removeProductFromTheCart(@PathVariable("id") Long id,
                                           Principal principal) {

        this.cartService.removeFromCart(id, principal);

        return "redirect:/cart";
    }

}
