package bg.softuni.fooddelivery.validation.product;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.ValidationErrorMessages.INVALID_PRODUCT_NAME;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidProductNameValidator.class)
public @interface ValidProductName {
    String message() default INVALID_PRODUCT_NAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
