package bg.softuni.fooddelivery.rest;

import bg.softuni.fooddelivery.domain.dto.view.OrderDetailViewDto;
import bg.softuni.fooddelivery.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order/details")
public class OrderDetailsController {

    private final OrderService orderService;

    public OrderDetailsController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailViewDto> getBookById(@PathVariable("id") Long id) {
        OrderDetailViewDto bookOpt = orderService.getOrderById(id);
        return ResponseEntity.ok(bookOpt);
    }


}
