package bg.softuni.fooddelivery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)
//how long annotation can be retained
@Target(ElementType.TYPE)
//Indicates the contexts in which  we can use this annotation
@Constraint(validatedBy = FieldMatchValidator.class)
public @interface FieldMatch {

    String firstField();

    String secondField();

    String message() default "Fields should match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
