package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.ContactBindingDto;
import bg.softuni.fooddelivery.domain.entities.ContactEntity;
import bg.softuni.fooddelivery.repositories.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    @Spy
    private ModelMapper mockModelMapper;

    @Mock
    private ContactRepository mockContactRepository;


    @Captor
    private ArgumentCaptor<ContactEntity> contactEntityArgumentCaptor;

    private ContactService serviceToTest;

    @BeforeEach
    void setUp() {
        serviceToTest = new ContactService(mockModelMapper, mockContactRepository);
    }

    @Test
    void testSavingContactForm() {
        ContactBindingDto contactBindingDto = new ContactBindingDto()
                .setName("contact")
                .setSubject("subject")
                .setEmail("email@email.com")
                .setDescription("description");

        serviceToTest.saveContactMessage(contactBindingDto);

        verify(mockContactRepository).saveAndFlush(contactEntityArgumentCaptor.capture());

        ContactEntity actualSaved = contactEntityArgumentCaptor.getValue();

        Assertions.assertEquals(contactBindingDto.getName(),actualSaved.getName());
        Assertions.assertEquals(contactBindingDto.getDescription(),actualSaved.getDescription());
        Assertions.assertEquals(contactBindingDto.getSubject(),actualSaved.getSubject());
        Assertions.assertEquals(contactBindingDto.getEmail(),actualSaved.getEmail());

    }

}
