package bg.softuni.fooddelivery.constants;

public enum ValidationErrorMessages {
    ;
    public static final String POSITIVE_PRICE = "Price must be positive.";
    public static final String PRICE_REQUIRED = "Price is required.";
    public static final String DESCRIPTION_REQUIRED = "Description is required.";
    public static final String SUBJECT_MINIMUM = "Subject must be at least 3 symbols.";
    public static final String DESCRIPTION_BETWEEN = "Description must be between 5 and 2000 symbols.";
    public static final String DESCRIPTION_NOT_EMPTY = "Cannot remove description.";
    public static final String EMAIL_UNIQUE = "Email should be unique.";
    public static final String AGE_PROVIDED = "Age should be provided.";

    public static final String MATCHING_PASSWORDS = "Passwords should match.";

    public static final String INVALID_CONTACT_NAME = "Name must be between 2 and 15 letters.";
    public static final String INVALID_EMAIL = "Email should be valid";

    public static final String INVALID_DISCOUNT = "Invalid discount code";
    public static final String INVALID_PHONE_NUMBER = "Invalid phone number";

    public static final String INVALID_PRODUCT_NAME = "Product name must be at least 3 letters.";
    public static final String INVALID_ADDRESS = "Address must be at least 5 symbols and contain the number of the building.";

    public static final String FIELDS_MATCH = "Fields should match";
    public static final String UNIQUE_USERNAME= "Username should be unique.";
    public static final String UNIQUE_EMAIL= "Email should be unique.";

    public static final String INVALID_USERNAME = "Username should be at least 4 characters and contains at least 1 digit.";
    public static final String INVALID_PASSWORD = "Password should be at least 8 characters and contain digit/s.";



}
