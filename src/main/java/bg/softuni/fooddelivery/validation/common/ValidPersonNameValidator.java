package bg.softuni.fooddelivery.validation.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidPersonNameValidator implements ConstraintValidator<ValidPersonName, String> {
    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if(value.length()<2 || value.length()>15){
            return false;
        }

        for(int i=0;i<value.length();i++){
            if(!Character.isLetter(value.charAt(i))){
                return false;
            }
        }

        return true;
    }

}
