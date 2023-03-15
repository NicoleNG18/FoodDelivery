package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.entities.ShoppingCartEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Optional;

@Service
public class CartService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final ShoppingCartRepository shoppingCartRepository;

    public CartService(UserRepository userRepository,
                       ProductRepository productRepository,
                       ShoppingCartRepository shoppingCartRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public long addToCart(Long id,
                          Principal principal) {

        Optional<UserEntity> user = this.userRepository.findUserEntityByUsername(principal.getName());
        ProductEntity product = this.productRepository.findById(id).get();

        user.ifPresent(userEntity -> userEntity.getShoppingCart().addProduct(product));

        user.get().getShoppingCart().setCountProducts(user.get().getShoppingCart().getCountProducts()+1);

        return user.get().getShoppingCart().getCountProducts();
    }

    public ShoppingCartEntity getNewCart() {
        ShoppingCartEntity shoppingCart = new ShoppingCartEntity();
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
        return shoppingCart;
    }
}
