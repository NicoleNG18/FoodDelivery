package bg.softuni.fooddelivery.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static bg.softuni.fooddelivery.constants.Messages.INVALID_USERNAME;
import static bg.softuni.fooddelivery.constants.Messages.UNIQUE_USERNAME;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueUserUsernameValidator.class)
public @interface UniqueUsername {

    String message() default UNIQUE_USERNAME;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
