package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class OrderService {

    private final UserService userService;

    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public List<ProductEntity> getProducts(Principal principal) {

        UserEntity user = this.userService.getUserByUsername(principal.getName());

        return user.getShoppingCart().getProducts();
    }

//    public OrderModelDto initializeOrder(){
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        UserEntity owner=this.userService.getUserByUsername(currentPrincipalName);
//
//
//        return new OrderModelDto().setOwner(owner);
//    }
}
