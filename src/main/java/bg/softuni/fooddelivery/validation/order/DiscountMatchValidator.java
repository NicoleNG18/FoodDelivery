package bg.softuni.fooddelivery.validation.order;

import bg.softuni.fooddelivery.domain.enums.Discount;
import bg.softuni.fooddelivery.validation.order.DiscountMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DiscountMatchValidator implements ConstraintValidator<DiscountMatch, String> {

    @Override
    public boolean isValid(String value,
                           ConstraintValidatorContext context) {

        if(value.equals("")){
            return true;
        }

        long count = Arrays.stream(Discount.values()).map(Enum::name).filter(d -> d.equals(value)).count();

        return count != 0;
    }
}
