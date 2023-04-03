package bg.softuni.fooddelivery.validation.order;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidAddressValidator implements ConstraintValidator<ValidAddress, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if (value.length() < 5) {
            return false;
        }

        int countDigits = 0;
        int countLetters = 0;
        for (int i = 0; i < value.length(); i++) {
          if(Character.isDigit(value.charAt(i))){
              countDigits++;
          }
            if(Character.isLetter(value.charAt(i))){
                countLetters++;
            }
        }

        return countDigits != 0 && countLetters != 0;
    }
}
