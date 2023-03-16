package bg.softuni.fooddelivery.service;
import bg.softuni.fooddelivery.domain.dto.view.ProductViewDto;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final UserService userService;

    private final ModelMapper modelMapper;


    public OrderService(UserService userService,
                        ModelMapper modelMapper) {
        this.userService = userService;
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
}
