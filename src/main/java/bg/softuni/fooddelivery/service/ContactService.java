package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.ContactFormBindingDto;
import bg.softuni.fooddelivery.domain.dto.model.ContactModelDto;
import bg.softuni.fooddelivery.domain.entities.ContactEntity;
import bg.softuni.fooddelivery.repositories.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    public ContactService(ModelMapper modelMapper,
                          ContactRepository contactRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
    }

    public ContactModelDto mapToModel(ContactFormBindingDto contactBinding) {
        return this.modelMapper.map(contactBinding, ContactModelDto.class);
    }

    public void saveContactMessage(ContactModelDto contactModel) {

        ContactEntity contactToSave =
                this.modelMapper.map(contactModel, ContactEntity.class);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        contactToSave.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));

        this.contactRepository.saveAndFlush(contactToSave);

    }
}
