package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents an ingredient's quantity in the list of ingredients
 * Guarantees: TODO
 */
public class IngredientQuantity {
    public static final String MESSAGE_QUANTITY_CONSTRAINTS =
            "Quantity should only contain numbers";
    public static final String QUANTITY_VALIDATION_REGEX = "\\d+";
    public int quantity;

    /**
     * Constructs an {@code IngredientQuantity}.
     *
     * @param quantity A valid quantity value expressed as a string.
     */
    public IngredientQuantity(String quantity) {
        requireNonNull(quantity);
        checkArgument(isValidQuantity(quantity), MESSAGE_QUANTITY_CONSTRAINTS);
        this.quantity = Integer.parseInt(quantity);
    }

    /**
     * Returns true if a given string is a valid quantity value.
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(QUANTITY_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return Integer.toString(quantity);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IngredientQuantity // instanceof handles nulls
                && quantity == (((IngredientQuantity) other).quantity)); // state check
    }

    @Override
    public int hashCode() {
        return quantity; // primitive int is its own hash
    }

}
