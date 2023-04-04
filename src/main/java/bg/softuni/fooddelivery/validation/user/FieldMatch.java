package bg.softuni.fooddelivery.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.Messages.FIELDS_MATCH;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String firstField();

    String secondField();

    String message() default FIELDS_MATCH;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
