package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.ContactBindingDto;
import bg.softuni.fooddelivery.service.ContactService;
import bg.softuni.fooddelivery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;
    private final UserService userService;

    @Autowired
    public ContactController(ContactService contactService,
                             UserService userService) {
        this.contactService = contactService;
        this.userService = userService;
    }

    @ModelAttribute("contactDto")
    public ContactBindingDto initBindingDto() {
        return new ContactBindingDto();
    }

    @GetMapping
    public String getContact(Principal principal,
                             Model model) {

//        model.addAttribute("countProducts",
//                this.userService.getUserByUsername(principal.getName()).getCart().getCountProducts());

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
