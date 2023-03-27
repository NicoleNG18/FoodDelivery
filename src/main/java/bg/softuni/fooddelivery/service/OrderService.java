package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.OrderBindingDto;
import bg.softuni.fooddelivery.domain.dto.view.OrderDetailViewDto;
import bg.softuni.fooddelivery.domain.dto.view.ProductViewDto;
import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
import bg.softuni.fooddelivery.exception.ObjectNotFoundException;
import bg.softuni.fooddelivery.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static bg.softuni.fooddelivery.constants.Messages.*;

@Service
public class OrderService {

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public OrderService(UserService userService,
                        OrderRepository orderRepository,
                        ModelMapper modelMapper) {
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public List<ProductViewDto> getProducts(Principal principal) {

        final UserEntity user = this.userService.getUserByUsername(principal.getName());

        return user
                .getCart()
                .getProducts()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductViewDto.class))
                .collect(Collectors.toList());
    }


    public BigDecimal getProductsPrice(Principal principal) {

        final UserEntity user = this.userService.getUserByUsername(principal.getName());

        return user.getCart().getProductsSum();

    }

    @Transactional
    public void makeOrder(OrderBindingDto orderDto,
                          Principal principal) {

        OrderEntity order = new OrderEntity();

        final UserEntity user = this.userService.getUserByUsername(principal.getName());

        buildOrder(orderDto, order, user);

        this.orderRepository.saveAndFlush(order);

        user.getCart().setProducts(new ArrayList<>()).setProductsSum(BigDecimal.ZERO);
    }

    private static void buildOrder(OrderBindingDto orderDto,
                                   OrderEntity order,
                                   UserEntity user) {
        order
                .setOwner(user)
                .setPrice(user.getCart().getProductsSum())
                .setCreatedOn(LocalDateTime.now())
                .setComment(orderDto.getComment() != null ? orderDto.getComment() : NO_COMMENT)
                .setAddress(orderDto.getAddress())
                .setContactNumber(orderDto.getContactNumber())
                .setStatus(OrderStatusEnum.IN_PROGRESS);
    }

    @Transactional
    public List<OrderDetailViewDto> getOrdersByUser(Principal principal) {

        final UserEntity user = this.userService.getUserByUsername(principal.getName());

        return this.orderRepository
                .findAllByOwner_Id(user.getId())
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    private OrderDetailViewDto mapToOrderViewDto(OrderEntity orderEntity) {

        OrderDetailViewDto orderDetail = this.modelMapper.map(orderEntity, OrderDetailViewDto.class);

        orderDetail.setClient(orderEntity.getOwner().getUsername());

        return orderDetail;
    }

    public OrderDetailViewDto getOrderById(Long id) {

        OrderEntity order = this.orderRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, ORDER));

        if (order.getComment().equals("")) {
            order.setComment(NO_COMMENT_MESSAGE);
        }

        return mapToOrderViewDto(order);
    }

    public List<OrderDetailViewDto> getAllOrders() {
        return this.orderRepository
                .findAll()
                .stream()
                .map(this::mapToOrderViewDto)
                .collect(Collectors.toList());
    }

    public void finishOrder(Long orderId) {

        OrderEntity orderEntity = this.orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ObjectNotFoundException(orderId, ORDER));

        orderEntity.setStatus(OrderStatusEnum.DELIVERED);
        orderEntity.setDeliveredOn(LocalDateTime.now());

        this.orderRepository.saveAndFlush(orderEntity);
    }
}
