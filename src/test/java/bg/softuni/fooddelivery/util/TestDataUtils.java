package bg.softuni.fooddelivery.util;

import bg.softuni.fooddelivery.domain.entities.*;
import bg.softuni.fooddelivery.domain.enums.GenderEnum;
import bg.softuni.fooddelivery.domain.enums.OrderStatusEnum;
import bg.softuni.fooddelivery.domain.enums.ProductCategoryEnum;
import bg.softuni.fooddelivery.domain.enums.UserRoleEnum;
import bg.softuni.fooddelivery.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class TestDataUtils {

    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private ShoppingCartRepository cartRepository;
    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    @Autowired
    public TestDataUtils(UserRoleRepository userRoleRepository,
                         UserRepository userRepository,
                         ShoppingCartRepository cartRepository,
                         ProductRepository productRepository,
                         OrderRepository orderRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            UserRoleEntity workerRole = new UserRoleEntity().setRole(UserRoleEnum.WORKER);

            userRoleRepository.save(adminRole);
            userRoleRepository.save(userRole);
            userRoleRepository.save(workerRole);
        }
    }

    public UserEntity createTestAdmin(String email,
                                      String username) {

        initRoles();

        UserEntity admin = new UserEntity()
                .setEmail(email)
                .setUsername(username)
                .setFirstName("Admin")
                .setLastName("Adminov")
                .setPassword("topsecret")
                .setGender(GenderEnum.FEMALE)
                .setAge(25)
                .setCart(createCart())
                .setOrders(new ArrayList<>())
                .setPhoneNumber("0789632145")
                .setRoles(userRoleRepository.findAll());

        return userRepository.save(admin);
    }

    public void addProduct(UserEntity user,
                           ProductEntity product) {
        user.getCart().addProduct(product);
    }


    public UserEntity createTestUser(String email,
                                     String username) {

        initRoles();

        UserEntity user = new UserEntity()
                .setEmail(email)
                .setUsername(username)
                .setFirstName("User")
                .setLastName("Userov")
                .setPassword("topsecret")
                .setGender(GenderEnum.FEMALE)
                .setAge(25)
                .setCart(createCart())
                .setOrders(new ArrayList<>())
                .setPhoneNumber("0789632145")
                .setRoles(userRoleRepository.
                        findAll().stream().
                        filter(r -> r.getRole() == UserRoleEnum.USER).
                        toList());

        return userRepository.save(user);
    }


    public CartEntity createCart() {

        CartEntity cart = new CartEntity();

        return cartRepository.save(cart);

    }

    public ProductEntity createProductBurger(String name) {

        ProductEntity product = new ProductEntity()
                .setPrice(BigDecimal.TEN)
                .setName(name)
                .setCategory(ProductCategoryEnum.burger)
                .setDescription("description product");

        return productRepository.saveAndFlush(product);
    }

    public ProductEntity createProductPizza(String name) {

        ProductEntity product = new ProductEntity()
                .setPrice(BigDecimal.TEN)
                .setName(name)
                .setCategory(ProductCategoryEnum.pizza)
                .setDescription("description product");

        return productRepository.saveAndFlush(product);
    }

    public OrderEntity createOrder() {

        OrderEntity order = new OrderEntity()
                .setPrice(BigDecimal.TEN)
                .setCreatedOn(LocalDateTime.now())
                .setAddress("orderAddress")
                .setContactNumber("0789654466")
                .setStatus(OrderStatusEnum.IN_PROGRESS);

        return orderRepository.save(order);
    }


    public void cleanUpDatabase() {
        userRepository.deleteAll();
        userRoleRepository.deleteAll();
        orderRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

}