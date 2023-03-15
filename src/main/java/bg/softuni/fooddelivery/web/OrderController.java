package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

//    @GetMapping("/cart")
//    public String getCart(Model model, Principal userDetails){
//
//
//        model.addAttribute("cartProducts",this.orderService.getProducts(userDetails));
//
//        return "order-cart";
//    }
}
