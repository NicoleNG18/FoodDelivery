package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import bg.softuni.fooddelivery.exception.WrongCategoryException;
import bg.softuni.fooddelivery.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    private ProductService serviceToTest;

    @Spy
    private ModelMapper mockModelMapper;

    @Mock
    private ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new ProductService(mockProductRepository, mockModelMapper);
    }


    @Test
    void wrongCategoryException() {
        Assertions.assertThrows(WrongCategoryException.class,
                () -> this.serviceToTest.findCategory("yeeeee"));
    }

    @Test
    void getProductByIdException() {
        Assertions.assertThrows(NotFoundObjectException.class,
                () -> this.serviceToTest.getProductById(-1L));
    }

}
