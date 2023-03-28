package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundObjectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView onProductNotFound(NotFoundObjectException objectNotFoundException){

        ModelAndView modelAndView=new ModelAndView("object-not-found");

        modelAndView.addObject("objectId",objectNotFoundException.getObjectId());
        modelAndView.addObject("objectType",objectNotFoundException.getObjectType());

        return modelAndView;
    }
}
