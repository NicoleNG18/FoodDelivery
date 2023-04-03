package bg.softuni.fooddelivery.web;

import bg.softuni.fooddelivery.exception.NotFoundObjectException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.OBJECT_ID;
import static bg.softuni.fooddelivery.constants.ControllerAttributesConstants.OBJECT_TYPE;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundObjectException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView onProductNotFound(NotFoundObjectException objectNotFoundException){

        ModelAndView modelAndView=new ModelAndView("object-not-found");

        modelAndView.addObject(OBJECT_ID,objectNotFoundException.getObjectId());
        modelAndView.addObject(OBJECT_TYPE,objectNotFoundException.getObjectType());

        return modelAndView;
    }

}
