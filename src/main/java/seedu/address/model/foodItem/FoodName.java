package seedu.address.model.foodItem;

import seedu.address.model.person.Name;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Food Item's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */

public class FoodName {
    public static final String MESSAGE_NAME_CONSTRAINTS =
            "Food names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String foodName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public FoodName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_NAME_CONSTRAINTS);
        foodName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return foodName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FoodName // instanceof handles nulls
                && foodName.equals(((FoodName) other).foodName)); // state check
    }

    @Override
    public int hashCode() {
        return foodName.hashCode();
    }

}
