package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model,
                             Principal principal) {


        model.addAttribute("user", this.userService.getUserViewByUsername(principal.getName()));


        return "user-profile";
    }

    @GetMapping("/profile/{id}")
    public String getProfileById(@PathVariable("id") Long id, Model model) {


        model.addAttribute("user", this.userService.getUserById(id));


        return "user-profile";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {

        model.addAttribute("users", this.userService.getAllUsers());

        return "all-users";
    }

    @GetMapping("/change/{id}")
    public String changeRoles(@PathVariable("id") Long id,Model model){
            model.addAttribute("user", this.userService.getUserById(id));

            return "roles-change";
    }

    @PatchMapping("/roles/remove/{id}/{name}")
    public String removeRole(@PathVariable("id") Long id,@PathVariable("name") String roleName){

        this.userService.removeRole(roleName,id);

        return "redirect:/users/change/{id}";
    }

    @PatchMapping("/roles/add/{id}/{name}")
    public String addRole(@PathVariable("id") Long id,@PathVariable("name") String roleName){

        this.userService.addRole(roleName,id);

        return "redirect:/users/change/{id}";
    }
}
