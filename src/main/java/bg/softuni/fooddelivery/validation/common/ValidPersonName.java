package bg.softuni.fooddelivery.validation.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.ValidationErrorMessages.INVALID_CONTACT_NAME;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidPersonNameValidator.class)
public @interface ValidPersonName {
    String message() default INVALID_CONTACT_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
