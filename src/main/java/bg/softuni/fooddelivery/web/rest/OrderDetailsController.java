package bg.softuni.fooddelivery.web.rest;

import bg.softuni.fooddelivery.domain.dto.view.OrderViewDto;
import bg.softuni.fooddelivery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderDetailsController {

    private final OrderService orderService;

    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/api/order/details/{id}")
    public ResponseEntity<OrderViewDto> getOrderById(@PathVariable("id") Long id) {

        OrderViewDto orderDetail = orderService.getOrderById(id);

        return ResponseEntity.ok(orderDetail);
    }

}
