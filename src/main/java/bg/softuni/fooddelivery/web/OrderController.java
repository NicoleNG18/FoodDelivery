package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.OrderBindingDto;
import bg.softuni.fooddelivery.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.*;


@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute("orderDto")
    public OrderBindingDto initOrderDto() {
        return new OrderBindingDto();
    }

    @GetMapping("/finalize")
    public String getFinalizeOrder(Model model,
                                   Principal principal) {

        model.addAttribute(FOOD_PRICE, this.orderService.getProductsPrice(principal.getName()));
        model.addAttribute(COUNT_PRODUCTS, this.orderService.getProductsInTheCart(principal.getName()).size());

        return "finalize-order";
    }

    @PostMapping("/finalize")
    public String postFinalizeOrder(@Valid OrderBindingDto orderDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("orderDto", orderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderDto"
                    , bindingResult);

            return "redirect:/orders/finalize";

        }

        this.orderService.makeOrder(orderDto, principal.getName());

        return "redirect:/";
    }

    @GetMapping("/history")
    public String getOwnOrdersHistory(Model model,
                                      Principal principal) {

        model.addAttribute(ORDERS, this.orderService.getOrdersByUser(principal.getName()));
        model.addAttribute(COUNT_PRODUCTS,this.orderService.getProductsInTheCart(principal.getName()).size());

        return "orders-history-user";
    }

    @GetMapping("/details/{id}")
    public String getOrderDetails(@PathVariable("id") Long id,
                                  Model model) {

        model.addAttribute(ORDER, this.orderService.getOrderById(id));
        model.addAttribute(ID_ATR, id);

        return "order-details-api";
    }

    @GetMapping("/all/history")
    public String getAllOrdersHistory(Model model) {

        model.addAttribute(ALL_ORDERS, this.orderService.getAllOrders());

        return "orders-history";
    }

    @PatchMapping("/finish/{id}")
    public String finishOrder(@PathVariable("id") Long orderId) {

        this.orderService.finishOrder(orderId);

        return "redirect:/orders/all/history";
    }

    @PatchMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long orderId) {

        this.orderService.cancelOrder(orderId);

        return "redirect:/orders/all/history";
    }

}
