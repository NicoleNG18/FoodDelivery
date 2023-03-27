package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.ContactBindingDto;
import bg.softuni.fooddelivery.repositories.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private  ContactRepository mockContactRepository;

    private ContactService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new ContactService(mockModelMapper, mockContactRepository);
    }


}
