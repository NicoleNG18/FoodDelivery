package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.ProductEntity;
import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.domain.entities.UserEntity;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.Principal;



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

    @Transactional
    public void addToCart(Long id,
                          Principal principal) {

        UserEntity user = this.userRepository.findByUsername(principal.getName());
        final ProductEntity product = this.productRepository.findProductEntityById(id);

        user.getCart().addProduct(product);
        user.getCart().increaseProductsSum(product.getPrice());

    }

    public CartEntity getNewCart() {

        CartEntity shoppingCart = new CartEntity();

        this.shoppingCartRepository.saveAndFlush(shoppingCart);

        return shoppingCart;
    }

    @Transactional
    public void removeFromCart(Long id,
                               Principal principal) {

        UserEntity user = this.userRepository.findByUsername(principal.getName());
        final ProductEntity product = this.productRepository.findProductEntityById(id);

        user.getCart().getProducts().remove(product);
        user.getCart().decreaseProductsSum(product.getPrice());

    }
}
