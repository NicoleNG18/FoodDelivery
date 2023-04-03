package bg.softuni.fooddelivery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.Messages.INVALID_EMAIL;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidEmailValidator.class)
public @interface ValidEmail {
    String message() default INVALID_EMAIL;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
