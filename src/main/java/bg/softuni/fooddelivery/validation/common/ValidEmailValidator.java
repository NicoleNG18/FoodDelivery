package bg.softuni.fooddelivery.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)(\\.[a-zA-Z]{2,5}){1,2}$");
        Matcher matcher = pattern.matcher(value);

        return matcher.find();
    }
}
