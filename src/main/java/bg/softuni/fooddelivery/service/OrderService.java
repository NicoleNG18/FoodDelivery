package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.OrderBindingDto;
import bg.softuni.fooddelivery.domain.dto.view.OrderDetailViewDto;
import bg.softuni.fooddelivery.domain.dto.view.ProductViewDto;
import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserService userService;

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;


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

        order
                .setOwner(user)
                .setPrice(user.getCart().getProductsSum())
                .setCreatedOn(LocalDateTime.now())
                .setComment(orderDto.getComment() != null ? orderDto.getComment() : "no comment")
                .setAddress(orderDto.getAddress())
                .setContactNumber(orderDto.getContactNumber())
                .setDelivered(false);

        this.orderRepository.saveAndFlush(order);

        user.getCart().setProducts(new ArrayList<>()).setProductsSum(BigDecimal.ZERO);
    }

    //TODO: start admin functuonalities - adding/editing/deleting products roles - patch mapping
    //TODO: worker functionalities - delivering order

    @Transactional
    public List<OrderDetailViewDto> getOrdersByUser(Principal principal) {

        final UserEntity user = this.userService.getUserByUsername(principal.getName());

        return this.orderRepository
                .findAllByOwner_Id(user.getId())
                .stream()
                .map(this::mapToOrderView)
                .collect(Collectors.toList());
    }

    private OrderDetailViewDto mapToOrderView(OrderEntity orderEntity) {
        OrderDetailViewDto orderDetail = this.modelMapper.map(orderEntity, OrderDetailViewDto.class);
        orderDetail.setClient(orderEntity.getOwner().getUsername());
        return orderDetail;
    }

    public OrderDetailViewDto getOrderById(Long id) {

       final OrderEntity order = this.orderRepository.findOrderEntityById(id);

       return mapToOrderView(order);
    }

    public List<OrderDetailViewDto> getAllOrders() {
        return this.orderRepository.findAll().stream().map(this::mapToOrderView).collect(Collectors.toList());
    }
}
