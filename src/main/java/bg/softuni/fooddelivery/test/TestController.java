package bg.softuni.fooddelivery.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

//    @GetMapping("/login")
//    public String getLogin(){
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String getRegister(){
//        return "register";
//    }
//
//    @GetMapping("/")
//    public String getHome(){
//        return "index";
//    }

    @GetMapping("/contact")
    public String getContact(){
        return "contact-us";
    }
    @GetMapping("/menu")
    public String getMenu(){
        return "menu-categories";
    }
    @GetMapping("/profile")
    public String getProfile(){
        return "user-profile";
    }
}
