package bg.softuni.fooddelivery.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClosedController {

    @GetMapping("/closed")
    public String getMaintenance(){
        return "closed";
    }
}
