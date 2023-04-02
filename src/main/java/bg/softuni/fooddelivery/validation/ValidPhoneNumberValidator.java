package bg.softuni.fooddelivery.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    @Override
    public boolean isValid(String number,
                           ConstraintValidatorContext context) {

        if(number.length()!=10){
            return false;
        }

        if (number.charAt(0) != 48) {
            return false;
        }

        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) < 48 || number.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

}