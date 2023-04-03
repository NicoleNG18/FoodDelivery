package bg.softuni.fooddelivery.validation.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if (value.length() < 8) {
            return false;
        }

        int countDigits=0;
        for (int i=0;i<value.length();i++){
            if(Character.isDigit(value.charAt(i))){
                countDigits++;
            }
        }

        return countDigits != 0;
    }
}
