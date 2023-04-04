package bg.softuni.fooddelivery.validation.order;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.Messages.INVALID_ADDRESS;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidAddressValidator.class)
public @interface ValidAddress {

    String message() default INVALID_ADDRESS;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
