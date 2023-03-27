package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.domain.dto.binding.EditUserBindingDto;
import bg.softuni.fooddelivery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("editedUser")
    public EditUserBindingDto initBindingDto() {
        return new EditUserBindingDto();
    }

    @GetMapping("/login")
    public String getLogin() {

        return "auth-login";
    }

    @PostMapping("/login-error")
    public String failedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
            String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("username", username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/profile")
    public String getProfile(Model model,
                             Principal principal) {


        model.addAttribute("user", this.userService.getUserViewByUsername(principal.getName()));

        return "user-profile";
    }

    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable("id") Long id,
                                 Model model) {

        model.addAttribute("user", this.userService.getUserById(id));

        return "user-profile";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", this.userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/change/{id}")
    public String changeRoles(@PathVariable("id") Long id,
                              Model model) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "roles-change";
    }

    @PatchMapping("/roles/remove/{id}")
    public String removeRole(@PathVariable("id") Long id) {

        this.userService.removeRole(id);

        return "redirect:/users/change/{id}";
    }

    @PatchMapping("/roles/add/{id}")
    public String addRole(@PathVariable("id") Long id) {

        this.userService.addRole(id);

        return "redirect:/users/change/{id}";
    }

    @GetMapping("/edit/{id}")
    public String getEditUser(@PathVariable("id") Long id,
                              Model model) {

        model.addAttribute("user", this.userService.getUserById(id));
//        if(!model.containsAttribute("errorUser")) {
//            model.addAttribute("errorUser", false);
//        }

        return "edit-user";
    }

    @PatchMapping("/edited/{id}")
    public String editedProduct(@PathVariable("id") Long id,
                                @Valid EditUserBindingDto editedUser,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("editedUser", editedUser);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editedUser"
                    , bindingResult);

            return "redirect:/users/edit/{id}";

        }

        if (this.userService.editUser(id, editedUser)) {
            return "redirect:/users/edit/" + id;
        }

        return "redirect:/users/profile/{id}";
    }
}
