package bg.softuni.fooddelivery.validation;

import bg.softuni.fooddelivery.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUserUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserRepository userRepository;

    public UniqueUserUsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        return this.userRepository.
                findUserEntityByUsername(username).
                isEmpty();
    }
}
