package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.ContactBindingDto;
import bg.softuni.fooddelivery.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute("contactDto")
    public ContactBindingDto initBindingDto() {
        return new ContactBindingDto();
    }

    @GetMapping
    public String getContact() {
        return "contact-us";
    }

    @PostMapping
    public String postContact(@Valid ContactBindingDto contactDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("contactDto", contactDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactDto"
                    , bindingResult);

            return "redirect:/contact";

        }

        this.contactService.saveContactMessage(contactDto);

        return "redirect:/";
    }

}
