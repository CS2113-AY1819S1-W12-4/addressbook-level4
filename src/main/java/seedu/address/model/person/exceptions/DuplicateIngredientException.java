package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Ingredients (Ingredients are considered duplicates
 * if they have the same name).
 */
public class DuplicateIngredientException extends RuntimeException {
    public DuplicateIngredientException() {
        super("Operation would result in duplicate ingredients");
    }
}
