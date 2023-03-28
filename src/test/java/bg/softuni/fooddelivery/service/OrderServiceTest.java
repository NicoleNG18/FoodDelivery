package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.OrderBindingDto;
import bg.softuni.fooddelivery.domain.dto.view.OrderDetailViewDto;
import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.OrderEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import bg.softuni.fooddelivery.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private UserService userService;
    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private ModelMapper modelMapper;

    @Captor
    private ArgumentCaptor<OrderEntity> orderEntityArgumentCaptor;

    private OrderService serviceToTest;
    private OrderEntity orderOne, orderTwo;


    @BeforeEach
    void setUp() {
        serviceToTest = new OrderService(userService, mockOrderRepository, modelMapper);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("user", "topsecret"));

        orderOne = new OrderEntity()
                .setStatus(OrderStatusEnum.IN_PROGRESS)
                .setPrice(BigDecimal.valueOf(10))
                .setCreatedOn(LocalDateTime.now())
                .setAddress("asdddd")
                .setContactNumber("contactnum");

        orderTwo = new OrderEntity()
                .setStatus(OrderStatusEnum.IN_PROGRESS)
                .setPrice(BigDecimal.valueOf(10))
                .setCreatedOn(LocalDateTime.now())
                .setAddress("asddssssdd")
                .setContactNumber("contasssctnum");

        mockOrderRepository.saveAndFlush(orderOne);
        mockOrderRepository.saveAndFlush(orderTwo);
    }


//    @Test
//    void findOrderByIdSuccessful() {
//        OrderDetailViewDto orderView = serviceToTest.getOrderById(1L);
//
//        Assertions.assertEquals(orderOne.getComment(),orderView.getComment());
//        Assertions.assertEquals(orderOne.getAddress(),orderView.getAddress());
//        Assertions.assertEquals(orderOne.getStatus(),orderView.getStatus());
//        Assertions.assertEquals(orderOne.getPrice(),orderView.getPrice());
//        Assertions.assertEquals(orderOne.getCreatedOn(),orderView.getCreatedOn());
//        Assertions.assertEquals(orderOne.getContactNumber(),orderView.getContactNumber());
//    }
    @Test
    void findOrderByIdNotSuccessful() {
        Assertions.assertThrows(NotFoundObjectException.class,
                () -> this.serviceToTest.getOrderById(-1L));
    }

    @Test
    void finishOrderByIdNotSuccessful() {
        Assertions.assertThrows(NotFoundObjectException.class,
                () -> this.serviceToTest.finishOrder(-1L));
    }

}
