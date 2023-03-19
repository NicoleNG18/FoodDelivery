package bg.softuni.fooddelivery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.Messages.INVALID_USERNAME;

@Retention(RetentionPolicy.RUNTIME)
//how long annotation can be retained
@Target(ElementType.FIELD)
//Indicates the contexts in which  we can use this annotation
@Constraint(validatedBy = UniqueUserEmailValidator.class)
public @interface UniqueUserEmail {

    String message() default INVALID_USERNAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
