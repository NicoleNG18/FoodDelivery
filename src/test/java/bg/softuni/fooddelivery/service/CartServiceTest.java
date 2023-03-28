package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.entities.CartEntity;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import bg.softuni.fooddelivery.repositories.ShoppingCartRepository;
import bg.softuni.fooddelivery.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private ShoppingCartRepository mockShoppingCartRepository;

    private CartService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new CartService(mockUserRepository, mockProductRepository, mockShoppingCartRepository);
    }

    @Test
    void testSaveInvoked() {

        serviceToTest.getNewCart();

        verify(mockShoppingCartRepository).saveAndFlush(any());

    }

    @Test
    void testReturnedCart() {

        //act
        CartEntity newCart = serviceToTest.getNewCart();

        //assert
        Assertions.assertEquals(0, newCart.getProducts().size());
        Assertions.assertEquals(BigDecimal.ZERO, newCart.getProductsSum());
    }

}
