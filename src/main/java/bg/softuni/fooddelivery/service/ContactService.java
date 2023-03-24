package bg.softuni.fooddelivery.service;

import bg.softuni.fooddelivery.domain.dto.binding.ContactBindingDto;
import bg.softuni.fooddelivery.domain.entities.ContactEntity;
import bg.softuni.fooddelivery.repositories.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static bg.softuni.fooddelivery.constants.Messages.DATE_TIME_NOW_PATTERN;

@Service
public class ContactService {

    private final ModelMapper modelMapper;
    private final ContactRepository contactRepository;

    public ContactService(ModelMapper modelMapper,
                          ContactRepository contactRepository) {
        this.modelMapper = modelMapper;
        this.contactRepository = contactRepository;
    }


    public void saveContactMessage(ContactBindingDto contactBinding) {

        ContactEntity contactToSave = mapToEntity(contactBinding);

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_NOW_PATTERN);
        contactToSave.setCreatedOn(LocalDateTime.parse(dateTimeFormatter.format(LocalDateTime.now())));

        this.contactRepository.saveAndFlush(contactToSave);

    }

    public ContactEntity mapToEntity(ContactBindingDto contactBinding) {
        return this.modelMapper.map(contactBinding, ContactEntity.class);
    }

}
